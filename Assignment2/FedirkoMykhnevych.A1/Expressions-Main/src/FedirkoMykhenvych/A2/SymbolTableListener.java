package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

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
	private int argCount;

	public SymbolTableListener(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, OfpParser parser) {
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
		enterScope(ctx);

		if (declaredFunctions.containsKey(functionName))
			ErrorPrinter
				.printFullError(parser, 
						ctx.IDENTIFIER().getSymbol(), 
						"Duplicate function declaration: ", 
						functionName,
						currentScope.getScopeName());
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
		Function currFunc = (Function) currentScope;

		OFPType paramType = OFPType.getTypeFor(ctx.type().getText());
		String paramIdentifier = ctx.IDENTIFIER().getText();
		
		if(currentScope.resolve(ctx.IDENTIFIER().getText()) != null)
			ErrorPrinter
				.printFullError(parser, 
					ctx.IDENTIFIER().getSymbol(), 
					"Duplicate param declaration: ", 
					paramIdentifier,
					currentScope.getScopeName());		
			
		Symbol param = new Symbol(paramIdentifier, paramType);
		param.setScope(currentScope);	
		currFunc.addParam(param);		
		argCount++;
	}

	@Override
	public void enterMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		if (mainWasDeclared) {
			ErrorPrinter.printFullError(parser, null, "function ", "main", currentScope.getScopeName());
		}

		mainWasDeclared = true;
		Scope declarationScope = new BaseScope(currentScope);
		currentScope = declarationScope;	
	}
	
	@Override
	public void exitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		exitScope();
	}

	
	@Override 
	public void enterWhileStatement(OfpParser.WhileStatementContext ctx) {
		Scope whileScope = new BaseScope(currentScope);		
		currentScope = whileScope;
	}
	
	@Override public void exitWhileStatement(OfpParser.WhileStatementContext ctx) {
		exitScope();
	}
	
	@Override 
	public void enterVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		Scope declarationScope = new BaseScope(currentScope);
		currentScope = declarationScope;		
	}

	@Override public void exitVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		
		OFPType type = OFPType.getTypeFor(ctx.type().getText());
		
		List<Symbol> symbols = ctx
				.variableDeclarators()
				.variableDeclarator()
				.stream()
				.map(x -> new Symbol(x.IDENTIFIER().getText(), type))
				.collect(Collectors.toList());
		

		for(Symbol s : symbols) {
			if(currentScope.resolve(s.getName()) != null)
				ErrorPrinter
					.printFullError(parser, 
						null, 
						"Duplicate variable declaration: ", 
						s.getName(),
						currentScope.getScopeName());		
			else
				currentScope.define(s);
		}
		
		exitScope();
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