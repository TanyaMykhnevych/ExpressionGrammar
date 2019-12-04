package FedirkoMykhenvych.A2;

import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionDeclarationContext;

public class FunctionListener extends OfpBaseListener {

	OfpParser parser;

	final Map<String, Function> declaredFunctions;

	ParseTreeProperty<Scope> scopes;

	Scope currentScope = null;

	boolean mainWasDeclared; // not sure if we need this

	public FunctionListener(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, OfpParser parser) {
		this.declaredFunctions = functions;
		this.scopes = scopes;
		this.parser = parser;
	}

	@Override
	public void enterFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		String functionName = ctx.IDENTIFIER().getText();
		Function function = new Function(functionName);
		currentScope = function;

		if (declaredFunctions.containsKey(functionName))
			ErrorPrinter.printFullError(parser, ctx.IDENTIFIER().getSymbol(), "Duplicate function declaration: ", functionName,
					currentScope.getScopeName());

		saveScope(ctx, function);
	}

	@Override
	public void exitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		if (mainWasDeclared) {
			ErrorPrinter.printFullError(parser, null, "function ", "main", currentScope.getScopeName());
		}

		mainWasDeclared = true;
	}

	@Override
	public void exitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {

	}

	private void saveScope(ParserRuleContext ctx, Function s) {
		declaredFunctions.put(s.getScopeName(), s);
		scopes.put(ctx, s);
	}
}