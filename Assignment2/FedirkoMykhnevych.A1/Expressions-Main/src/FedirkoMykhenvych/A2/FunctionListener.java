package FedirkoMykhenvych.A2;

import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionDeclarationContext;
import FedirkoMykhnevych.A2.OfpParser.ReturnValueContext;

public class FunctionListener extends OfpBaseListener {

	OfpParser parser;

	final Map<String, Function> declaredFunctions;
	ParseTreeProperty<Scope> scopes;

	Scope currentScope = null;

	private boolean mainWasDeclared;
	private int argCount;

	public FunctionListener(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, OfpParser parser) {
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
		enterScope(ctx);

		if (declaredFunctions.containsKey(functionName))
			ErrorPrinter
				.printFullError(parser, 
						ctx.IDENTIFIER().getSymbol(), 
						"Duplicate function declaration: ", 
						functionName,
						currentScope.getScopeName());

		currentScope = function;

		saveScope(ctx, (Function) currentScope);
	}

	@Override
	public void exitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		exitScope();
	}

	@Override
	public void enterParamsList(OfpParser.ParamsListContext ctx) {
		argCount = 0;
	}

	@Override
	public void enterParam(OfpParser.ParamContext ctx) {
		Symbol param = currentScope.resolve(ctx.IDENTIFIER().getText());

	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		if (mainWasDeclared) {
			ErrorPrinter.printFullError(parser, null, "function ", "main", currentScope.getScopeName());
		}

		mainWasDeclared = true;
	}

	private void saveScope(ParserRuleContext ctx, Function s) {
		declaredFunctions.put(s.getScopeName(), s);
		scopes.put(ctx, s);
	}

	private void exitScope() {
		currentScope = currentScope.getEnclosingScope();
	}

	private void enterScope(ParserRuleContext ctx) {
		currentScope = scopes.get(ctx);
	}
}