package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionCallContext;

public class TypeCheckingVisitor extends OfpBaseVisitor<OFPType> {

	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private Scope globalScope;
	private Function currentFunction; // For logging purposes
	int errorCount = 0; // Keep track of the total number of errors

	private final OfpParser parser;

	public TypeCheckingVisitor(ParseTreeProperty<Scope> scopes, OfpParser parser) {
		this.scopes = scopes;
		this.parser = parser;
	}

	@Override
	public OFPType visitConditionExpression(OfpParser.ConditionExpressionContext ctx) {
		OFPType t = visit(ctx.getChild(2));
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitParamsList(OfpParser.ParamsListContext ctx) {
		FunctionCallContext parent = (FunctionCallContext) ctx.getParent();
		String fName = parent.getChild(0).getText();
		Function fSymbol = (Function) globalScope.resolve(fName);

		List<OFPType> pTypes = fSymbol.getParamTypes();

		List<OFPType> argTypes = new ArrayList<OFPType>();
		for (int i = 0; i < ctx.getChildCount(); i += 2) { // +2 because of commas
			OFPType argType = visit(ctx.getChild(i));
			argTypes.add(argType);
		}

		// Compare number of args vs parameters
		String eMsg = errorCount + "\tParam/arg mismatch in call to function " + fName + " in function "
				+ currentFunction.getName();
		if (argTypes.size() != pTypes.size()) { // Error
			errorCount++;
			System.out.println(eMsg);
			return null;
		}

		// Compare arg types
		for (int i = 0; i < pTypes.size(); i++) {
			OFPType pType = pTypes.get(i);
			OFPType aType = argTypes.get(i);
			if (pType == aType)
				; // OK
			else { // Error
				errorCount++;
				System.out.println(eMsg);
				return null;
			}
		}
		return null;
	}

	@Override
	public OFPType visitStart(OfpParser.StartContext ctx) {
		globalScope = currentScope;
		return null;
	}

	@Override
	public OFPType visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentFunction = (Function) currentScope;
		return null;
	}
}
