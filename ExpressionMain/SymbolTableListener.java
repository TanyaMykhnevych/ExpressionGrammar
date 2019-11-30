package mykhnevych.a2;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

public class SymbolTableListener extends ExpressionGrammarBaseListener {

	private ParseTreeProperty<BaseScope> scopes = new ParseTreeProperty<BaseScope>();

	public ParseTreeProperty<BaseScope> getSymbolTable() {
		return scopes;
	}

	private BaseScope currentScope = null;
	private FunctionSymbol currentFunctionSymbol = null; // Useful for later stages

	@Override
	public void enterStart(ExpressionGrammarParser.StartContext ctx) {
		// enclosing scope == null for the global/program scope
		currentScope = new BaseScope(null);
		scopes.put(ctx, currentScope);
	}

	@Override
	public void enterFunctionDeclaration(ExpressionGrammarParser.FunctionDeclarationContext ctx) {

	}

	@Override
	public void exitFunctionDeclaration(ExpressionGrammarParser.FunctionDeclarationContext ctx) {

	}
	
	@Override public void enterBlock(ExpressionGrammarParser.BlockContext ctx) {
		enterScope(ctx);
	}
	

	@Override public void exitBlock(ExpressionGrammarParser.BlockContext ctx) {
		exitScope();
	}

	public void enterScope(ParserRuleContext ctx) {
		BaseScope explicitScope = new BaseScope(currentScope);
		currentScope = explicitScope;
		saveScope(ctx, currentScope);
	}

	public void exitScope() {
		currentScope = currentScope.getEnclosingScope();
	}

	private void saveScope(ParserRuleContext ctx, BaseScope s) {
		scopes.put(ctx, s);
	}
}
