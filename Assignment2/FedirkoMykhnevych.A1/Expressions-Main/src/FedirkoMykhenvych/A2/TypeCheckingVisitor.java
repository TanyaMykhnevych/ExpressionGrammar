package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;

public class TypeCheckingVisitor extends OfpBaseVisitor<OFPType> {

	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private Scope globalScope;
	private Function currentFunction; // For logging purposes
	int errorCount = 0; // Keep track of the total number of errors
	final Map<String, Function> declaredFunctions;

	private final OfpParser parser;

	public TypeCheckingVisitor(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes,
			OfpParser parser) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
		this.parser = parser;
	}

	@Override
	public OFPType visitConditionExpression(OfpParser.ConditionExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentFunction = (Function) currentScope;
		globalScope = currentScope;
		enterScope(ctx);
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitParamsList(OfpParser.ParamsListContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentFunction = (Function) currentScope;
		globalScope = currentScope;
		enterScope(ctx);
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		String fName = ctx.getChild(0).getText();
		Function fSymbol = declaredFunctions.get(fName);

		List<OFPType> pTypes = fSymbol.getParamTypes();

		List<OFPType> argTypes = new ArrayList<OFPType>();
		for (int i = 0; i < ctx.getChildCount(); i += 2) { // +2 because of commas
			OFPType argType = visit(ctx.getChild(i));
			argTypes.add(argType);
		}

		// Compare number of args vs parameters
		String eMsg = errorCount + "\tParam/arg mismatch in call to function " + fName + " in function "
				+ fSymbol.getName();
		if (argTypes.size() != pTypes.size()) { // Error
			errorCount++;
			System.out.println(eMsg);
			return null;
		}
		return visitChildren(ctx);
	}

	private void enterScope(ParserRuleContext ctx) {
		currentScope = scopes.get(ctx);
		if (currentScope == null)
			throw new RuntimeException("No current scope in enterScope!");
	}
}
