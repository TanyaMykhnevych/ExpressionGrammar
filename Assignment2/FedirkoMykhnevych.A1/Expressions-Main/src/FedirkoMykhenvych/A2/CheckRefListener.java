package FedirkoMykhenvych.A2;

import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;

public class CheckRefListener extends OfpBaseListener {
	private ParseTreeProperty<Scope> scopes; // node-to-scope mapping
	private Scope currentScope;
	final Map<String, Function> declaredFunctions;

	private String currentFunction;
	int errorCount = 0; // Keep track of the total number of errors

	public CheckRefListener(Map<String, Function> functions, ParseTreeProperty<Scope> scopes) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
	}

	public int getErrorCount() {
		return errorCount;
	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentFunction = "main";
	}

	@Override
	public void exitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
	}

	@Override
	public void enterFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentFunction = ctx.IDENTIFIER().getText();
	}

	@Override
	public void enterIdentifierExpression(OfpParser.IdentifierExpressionContext ctx) {
		String identifier = ctx.IDENTIFIER().getText();
		Function function = declaredFunctions.get(currentFunction);
		Symbol usedSymbol = function.resolve(identifier);
        if(usedSymbol == null){
            // ErrorPrinter.printVariableMayNotHaveBeenInitializedError(parser, ctx.IDENTIFIER().getSymbol(), identifier);
			errorCount++;
			System.out.println(errorCount + "\nUndeclared variable use in function " + currentFunction + ": " + identifier);
        }
	}

	@Override
	public void enterFunctionCall(OfpParser.FunctionCallContext ctx) {
		String name = ctx.getChild(0).getText();
		Symbol sym = declaredFunctions.get(name);
		if (sym == null || !(sym instanceof Function)) {
			errorCount++;
			System.out.println(errorCount + "\nUndeclared function call in function " + currentFunction + ": " + name);
		}
	}

	@Override
	public void exitFunctionCall(OfpParser.FunctionCallContext ctx) {

	}
}
