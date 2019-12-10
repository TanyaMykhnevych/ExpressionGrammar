package FedirkoMykhenvych.A2;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class CheckRefListener {
	private ParseTreeProperty<Scope> scopes; // node-to-scope mapping
	private Scope currentScope;
	private Scope globalScope; // used to resolve function symbols

	private String currentFunction; // For logging purposes
	int errorCount = 0; // Keep track of the total number of errors

	public CheckRefListener(ParseTreeProperty<Scope> scopes) {
		this.scopes = scopes;
	}

	public int getErrorCount() {
		return errorCount;
	}

	private void enterScope(ParserRuleContext ctx) {
		currentScope = scopes.get(ctx);
		if (currentScope == null)
			throw new RuntimeException("No current scope in enterScope!");
	}

	private void exitScope() {
	}

	/*@Override
	public void enterProgram(OfpParser.ProgramContext ctx) {
		enterScope(ctx);
		globalScope = currentScope; // Save the ref. to the global/program scope
	}

	@Override
	public void enterCall(OfpParser.CallContext ctx) {
		String name = ctx.getChild(0).getText();
		Symbol sym = globalScope.resolve(name);
		if (sym == null || !(sym instanceof FunctionSymbol)) {
			errorCount++;
			System.out.println(errorCount + "\tUndeclared function call in function " + currentFunction + ": " + name);
		}
	}*/
}
