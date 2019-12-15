package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionDeclarationContext;
import FedirkoMykhnevych.A2.OfpParser.ReturnValueContext;

public class SymbolTableListener extends OfpBaseListener {

	OfpParser parser;

	final Map<String, Function> declaredFunctions;
	ParseTreeProperty<Scope> scopes;

	Scope currentScope = null;

	private boolean mainWasDeclared;

	public SymbolTableListener(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes,
			OfpParser parser) {
		this.declaredFunctions = functions;
		this.scopes = scopes;
		this.parser = parser;
	}

	@Override
	public void enterFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		String functionName = ctx.IDENTIFIER().getText();
		ReturnValueContext returnCtx = ctx.returnValue();
		OFPType returnType = returnCtx == null ? OFPType.voidType : OFPType.getTypeFor(returnCtx.getText());
		Function function = new Function(functionName, returnType);

		saveScope(ctx, function);

		if (declaredFunctions.containsKey(functionName))
			ErrorPrinter.printFullError(parser, ctx.IDENTIFIER().getSymbol(), "Duplicate function declaration: ",
					functionName, currentScope.getScopeName());

		declaredFunctions.put(functionName, function);
		enterScope(function);
	}

	@Override
	public void exitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		exitScope();
	}

	@Override
	public void enterParam(OfpParser.ParamContext ctx) {
		Function currFunc = (Function) currentScope;

		OFPType paramType = OFPType.getTypeFor(ctx.type().getText());
		String paramIdentifier = ctx.IDENTIFIER().getText();

		if (currentScope.resolve(ctx.IDENTIFIER().getText()) != null)
			ErrorPrinter.printFullError(parser, ctx.IDENTIFIER().getSymbol(), "Duplicate param declaration: ",
					paramIdentifier, currentScope.getScopeName());

		Symbol param = new Symbol(paramIdentifier, paramType);
		param.setScope(currentScope);
		currFunc.addParam(param);
	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		if (mainWasDeclared) {
			ErrorPrinter.printFullError(parser, null, "function ", "main", currentScope.getScopeName());
		}

		mainWasDeclared = true;
		Function mainFunction = new Function("main", OFPType.voidType);
		saveScope(ctx, mainFunction);
		enterScope(mainFunction);
		declaredFunctions.put("main", mainFunction);
	}

	@Override
	public void exitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		exitScope();
	}

	@Override
	public void enterIfStatement(OfpParser.IfStatementContext ctx) {
		Scope ifScope = new BaseScope(currentScope);
		saveScope(ctx, ifScope);
		enterScope(ifScope);
	}

	@Override
	public void exitIfStatement(OfpParser.IfStatementContext ctx) {
		exitScope();
	}

	@Override
	public void enterWhileStatement(OfpParser.WhileStatementContext ctx) {
		Scope whileScope = new BaseScope(currentScope);
		saveScope(ctx, whileScope);
		enterScope(whileScope);
	}

	@Override
	public void exitWhileStatement(OfpParser.WhileStatementContext ctx) {
		exitScope();
	}

	@Override
	public void enterVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {

		OFPType type = OFPType.getTypeFor(ctx.type().getText());

		List<TerminalNode> identifiers = ctx.variableDeclarators().variableDeclarator().stream()
				.map(x -> x.IDENTIFIER()).collect(Collectors.toList());

		for (TerminalNode tn : identifiers) {
			Symbol s = new Symbol(tn.getText(), type);

			if (currentScope.resolve(s.getName()) != null)
				ErrorPrinter.printFullError(parser, tn.getSymbol(), "Duplicate variable declaration: ", s.getName(),
						currentScope.getScopeName());
			else
				currentScope.define(s);
		}
	}

	private void exitScope() {
		currentScope = currentScope.getEnclosingScope();
	}

	private void enterScope(Scope scope) {
		currentScope = scope;
	}

	private void saveScope(ParserRuleContext ctx, Scope s) {
		scopes.put(ctx, s);
	}
}