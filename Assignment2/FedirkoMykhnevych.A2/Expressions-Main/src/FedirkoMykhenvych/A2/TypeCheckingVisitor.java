package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;

public class TypeCheckingVisitor extends OfpBaseVisitor<OFPType> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
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
	public OFPType visitBoolLiteralExpression(OfpParser.BoolLiteralExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitIntLiteralExpression(OfpParser.IntLiteralExpressionContext ctx) {
		return OFPType.intType;
	}

	@Override
	public OFPType visitCharLiteralExpression(OfpParser.CharLiteralExpressionContext ctx) {
		return OFPType.charType;
	}

	@Override
	public OFPType visitStringLiteralExpression(OfpParser.StringLiteralExpressionContext ctx) {
		return OFPType.stringType;
	}

	@Override
	public OFPType visitFloatLiteralExpression(OfpParser.FloatLiteralExpressionContext ctx) {
		return OFPType.floatType;
	}

	@Override
	public OFPType visitGtLtExpression(OfpParser.GtLtExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitCompareExpression(OfpParser.CompareExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitBooleanLiteralExpression(OfpParser.BooleanLiteralExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitCompareBooleanExpression(OfpParser.CompareBooleanExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitGtLtBooleanExpression(OfpParser.GtLtBooleanExpressionContext ctx) {
		return OFPType.boolType;
	}

	@Override
	public OFPType visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		String fName = ctx.getChild(0).getText();
		Function fSymbol = declaredFunctions.get(fName);

		if (fSymbol == null) {
			errorCount++;
			return visitChildren(ctx);
		}

		List<OFPType> pTypes = fSymbol.getParamTypes();

		List<OFPType> argTypes = new ArrayList<OFPType>();
		ParseTree arguments = ctx.arguments().getChild(1);
		// + 2 because of commas
		for (int i = 0; i < arguments.getChildCount(); i += 2) {
			OFPType argType = visit(arguments.getChild(i));
			argTypes.add(argType);
		}

		// Compare number of args vs parameters
		String eMsg = errorCount + "\nParam/arg mismatch in call to function " + fName + " in function "
				+ currentFunction.getName();
		if (argTypes.size() != pTypes.size()) {
			errorCount++;
			System.out.println(eMsg);
			return null;
		}
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentScope = scopes.get(ctx);
		currentFunction = (Function) currentScope;
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentScope = scopes.get(ctx);
		currentFunction = (Function) currentScope;
		return visitChildren(ctx);
	}

	/*@Override
	public OFPType visitAddSubExpression(OfpParser.AddSubExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));
		if (lhsType != rhsType) { // Error
			errorCount++;
			System.out.println(errorCount + "\nIncompatible plus / minus op types in " + "function " + currentFunction
					+ ": " + lhsType + ", " + rhsType);
			return lhsType;
		}
		return lhsType;
	}
	
	@Override
	public OFPType visitMulDivExpression(OfpParser.MulDivExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));
		if (lhsType != rhsType) { // Error
			errorCount++;
			System.out.println(errorCount + "\nIncompatible mul / div op types in " + "function " + currentFunction
					+ ": " + lhsType + ", " + rhsType);
			return lhsType;
		}
		return lhsType;
	}
	
	@Override
	public OFPType visitWhileStatement(OfpParser.WhileStatementContext ctx) {
		currentScope = scopes.get(ctx);
		OFPType booleanExpression = visit(ctx.conditionExpression());
		visit(ctx.statement());
		if (booleanExpression.getName() != OFPType.boolType.getName()) {
			errorCount++;
			ErrorPrinter.printFullError(parser, ctx.WHILE().getSymbol(), "Incompatible types in while condition: ",
					OFPType.boolType.toString(), booleanExpression.toString());
		}
		return null;
	}*/

	@Override
	public OFPType visitConditionExpression(OfpParser.ConditionExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionCallBooleanExpression(OfpParser.FunctionCallBooleanExpressionContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitIdentifierBooleanExpression(OfpParser.IdentifierBooleanExpressionContext ctx) {
		return visitChildren(ctx);
	}
}
