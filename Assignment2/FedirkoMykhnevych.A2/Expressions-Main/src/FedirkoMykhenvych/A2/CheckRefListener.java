package FedirkoMykhenvych.A2;

import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;

public class CheckRefListener extends OfpBaseListener {
	private ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	final Map<String, Function> declaredFunctions;

	private String currentFunction;

	public CheckRefListener(Map<String, Function> functions, ParseTreeProperty<Scope> scopes) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
	}

	@Override
	public void enterFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentFunction = ctx.IDENTIFIER().getText();
		enterScope(ctx);
	}

	@Override
	public void exitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		exitScope();
	}

	@Override
	public void enterIfStatement(OfpParser.IfStatementContext ctx) {
		enterScope(ctx);
	}

	@Override
	public void exitIfStatement(OfpParser.IfStatementContext ctx) {
		exitScope();
	}

	@Override
	public void enterWhileStatement(OfpParser.WhileStatementContext ctx) {
		enterScope(ctx);
	}

	@Override
	public void exitWhileStatement(OfpParser.WhileStatementContext ctx) {
		exitScope();
	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentFunction = "main";
		enterScope(ctx);
	}

	@Override
	public void exitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		exitScope();
	}

	@Override
	public void enterIdentifierExpression(OfpParser.IdentifierExpressionContext ctx) {
		String identifier = ctx.IDENTIFIER().getText();
		handleUndeclaredVariableUse(identifier);
	}

	@Override
	public void enterAssignStatement(OfpParser.AssignStatementContext ctx) {
		TerminalNode identifierNode = ctx.IDENTIFIER();
		if (identifierNode != null) {
			String identifier = ctx.IDENTIFIER().getText();
			handleUndeclaredVariableUse(identifier);
		}
	}

	@Override
	public void enterFunctionCall(OfpParser.FunctionCallContext ctx) {
		String name = ctx.getChild(0).getText();
		Symbol sym = declaredFunctions.get(name);
		if (sym == null || !(sym instanceof Function)) {
			ErrorPrinter.printRawString("Undeclared function call in function " + currentFunction + ": " + name);
		}
	}

	private void handleUndeclaredVariableUse(String identifier) {
		Symbol usedSymbol = currentScope.resolve(identifier);

		if (usedSymbol == null) {
			ErrorPrinter.printRawString("Undeclared variable use in function " + currentFunction + ": " + identifier);
		}
	}

	public void enterScope(ParserRuleContext ctx) {
		currentScope = scopes.get(ctx);
	}

	private void exitScope() {
		currentScope = currentScope.getEnclosingScope();
	}
}
