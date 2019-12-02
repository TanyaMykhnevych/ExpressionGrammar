package FedirkoMykhenvych.A2;

import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseListener;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionDeclarationContext;

public class FunctionListener extends OfpBaseListener{
	
	OfpParser parser;
	
	final Map<String, Function> declaredFunctions;
	
	ParseTreeProperty<Scope> scopes;
	
	Scope currentScope = null;
	
	boolean isMain; // not sure if we need this
	
	public FunctionListener(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, OfpParser parser) {
		this.declaredFunctions = functions;
		this.scopes = scopes;
		this.parser = parser;	
	}
	
	@Override public void enterFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		String functionName = ctx.IDENTIFIER().getText();		
		Function function = declaredFunctions.get(functionName);
		currentScope = function;
		
		if(functionName == "main") {
			String returnValue = ctx.returnValue().getText();
			if(returnValue != "void")
					ErrorPrinter
						.printFullError(
							parser, 
							ctx.IDENTIFIER().getSymbol(), 
							"error: main() function must be void", 
							functionName, 
							"location" + currentScope.getScopeName());
		}
		else {			
			
			if(declaredFunctions.containsKey(functionName))
				ErrorPrinter
					.printFullError(
						parser, 
						ctx.IDENTIFIER().getSymbol(), 
						"function ", 
						functionName, 
						currentScope.getScopeName());
		}
		
		//saveScope(ctx, function);
	}
	
	@Override public void exitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentScope = currentScope.getEnclosingScope();
	}
	
	private void saveScope(ParserRuleContext ctx, Scope s) {
		scopes.put(ctx, s);
	}
}