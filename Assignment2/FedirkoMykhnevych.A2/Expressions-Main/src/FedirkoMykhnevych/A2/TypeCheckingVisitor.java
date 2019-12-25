package FedirkoMykhnevych.A2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;

public class TypeCheckingVisitor extends OfpBaseVisitor<OFPType> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private Function currentFunction; // For logging purposes
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
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));

		if (lhsType.equals(OFPType.stringType) || rhsType.equals(OFPType.stringType)) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to string comparison");
			return null;
		}

		if (lhsType.IsArrayType() || rhsType.IsArrayType()) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to compare arrays");
			return null;
		}

		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to operands of type " + "'" + lhsType + "'"
					+ " and " + "'" + rhsType + "'");
			return null;
		} else {
			return OFPType.boolType;
		}
	}

	@Override
	public OFPType visitBooleanLiteralExpression(OfpParser.BooleanLiteralExpressionContext ctx) {
		return OFPType.boolType;
	}

	// same as visitGtLtExpression
	@Override
	public OFPType visitGtLtBooleanExpression(OfpParser.GtLtBooleanExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));

		if (lhsType.equals(OFPType.stringType) || rhsType.equals(OFPType.stringType)) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to string comparison");
			return null;
		}

		if (lhsType.IsArrayType() || rhsType.IsArrayType()) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to compare arrays");
			return null;
		}

		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Operatos '>', '<' cannot be applied to operands of type " + "'" + lhsType + "'"
					+ " and " + "'" + rhsType + "'");
			return null;
		} else {
			return OFPType.boolType;
		}
	}

	@Override
	public OFPType visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		String fName = ctx.getChild(0).getText();
		Function fSymbol = declaredFunctions.get(fName);

		List<OFPType> pTypes = fSymbol.getParamTypes();

		List<OFPType> argTypes = new ArrayList<OFPType>();
		ParseTree arguments = ctx.arguments().getChild(1);
		// + 2 because of commas
		for (int i = 0; i < arguments.getChildCount(); i += 2) {
			OFPType argType = visit(arguments.getChild(i));
			argTypes.add(argType);
		}

		// Compare number of args vs parameters
		if (argTypes.size() != pTypes.size()) {
			String eMsg = "Param/arg mismatch in call to function " + fName + " in function "
					+ currentFunction.getName();

			ErrorPrinter.printRawString(eMsg);
			return fSymbol.getType();
		}

		for (int i = 0; i < argTypes.size(); i++) {
			OFPType argType = argTypes.get(i);
			OFPType paramType = pTypes.get(i);

			if (argType.getName() != paramType.getName()) {
				ErrorPrinter.printRawString(i + "'s argument type doesn't match delcared functionParam");
				return fSymbol.getType();
			}

		}

		return fSymbol.getType();
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

	@Override
	public OFPType visitUnaryMinusExpression(OfpParser.UnaryMinusExpressionContext ctx) {
		OFPType number = visit(ctx.expression());
		if (number.getName() != OFPType.intType.getName() && number.getName() != OFPType.floatType.getName()) {
			ErrorPrinter.printFileNameAndLineNumber(ctx.SUB().getSymbol());
			System.err.println("error: bad operand type " + number + " for unary operator '-'");
			ErrorPrinter.underlineError(parser, ctx.SUB().getSymbol());
		}
		return OFPType.boolType;
	}

	@Override
	public OFPType visitAddSubExpression(OfpParser.AddSubExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));

		if (!lhsType.equals(rhsType)) { // Error
			ErrorPrinter.printRawString("Incompatible plus / minus op types in function " + currentFunction + ": "
					+ lhsType + ", " + rhsType);
		}

		if (lhsType.equals(OFPType.stringType) || rhsType.equals(OFPType.stringType)) {
			ErrorPrinter.printRawString("String concatenation is not allowed!");
		}

		return lhsType;
	}

	@Override
	public OFPType visitMulDivExpression(OfpParser.MulDivExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));
		
		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString(
					"Incompatible mul / div op types in function " + currentFunction + ": " + lhsType + ", " + rhsType);
			return lhsType;
		}

		if (!lhsType.IsNumberType() || !rhsType.IsNumberType()) {

			ErrorPrinter.printRawString("\n multiply or divide operations can be performed only between numeric type");
		}

		// todo: only with int and float
		return lhsType;
	}
	
	@Override
	public OFPType visitBracketsExpression(OfpParser.BracketsExpressionContext ctx) {
		return visit(ctx.expression());
	}
	

	// todo: The comparison operator == can also be applied on characters (but not
	// strings)
	@Override 
	public OFPType visitArrayElementAssignStatement(OfpParser.ArrayElementAssignStatementContext ctx) { 
		String variableId = ctx.IDENTIFIER().getText();
		OFPType lhsType = currentScope.resolve(variableId).getType().FromArrayToPrimitive();
		
		// '1' beacause of grammar
		OFPType rhsType = visit(ctx.expression(1));
		
		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Invalid array element assign statement: incompatible types");
		}
		
		return null;
	}
	
	@Override 
	public OFPType visitVariableAssignStatement(OfpParser.VariableAssignStatementContext ctx) { 
		String variableId = ctx.IDENTIFIER().getText();
		OFPType lhsType = currentScope.resolve(variableId).getType();
		OFPType rhsType = visit(ctx.expression());
		
		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Invalid variable assign statement: incompatible types");
		} 
		
		return null;
	}

	@Override
	public OFPType visitWhileStatement(OfpParser.WhileStatementContext ctx) {
		currentScope = scopes.get(ctx);

		OFPType booleanExpression = visit(ctx.conditionExpression().booleanExpression());

		if (!booleanExpression.equals(OFPType.boolType)) {
			ErrorPrinter.printFullError(parser, ctx.WHILE().getSymbol(), "Incompatible types in while condition: ",
					OFPType.boolType.toString(), booleanExpression.toString());
		}
		return null;
	}

	@Override
	public OFPType visitEqualsExpression(OfpParser.EqualsExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));

		if (lhsType.equals(OFPType.stringType) || rhsType.equals(OFPType.stringType)) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to string comparison");

			return null;
		}

		if (lhsType.IsArrayType() || rhsType.IsArrayType()) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to compare arrays");
			return null;
		}

		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to operands of type " + "'" + lhsType + "'"
					+ " and " + "'" + rhsType + "'");
			return null;
		} else {
			return OFPType.boolType;
		}
	}

	// Same as 'visitEqualsExpression()'
	@Override
	public OFPType visitBooleanEqualsExpression(OfpParser.BooleanEqualsExpressionContext ctx) {
		OFPType lhsType = visit(ctx.expression(0));
		OFPType rhsType = visit(ctx.expression(1));

		if (lhsType.equals(OFPType.stringType) || rhsType.equals(OFPType.stringType)) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to string comparison");

			return null;
		}

		if (lhsType.IsArrayType() || rhsType.IsArrayType()) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to compare arrays");
			return null;
		}

		if (!lhsType.equals(rhsType)) {
			ErrorPrinter.printRawString("Operator '==' cannot be applied to operands of type " + "'" + lhsType + "'"
					+ " and " + "'" + rhsType + "'");
			return null;
		} else {
			return OFPType.boolType;
		}
	}

	@Override
	public OFPType visitBuiltintFunctionArgument(OfpParser.BuiltintFunctionArgumentContext ctx) {
		OFPType argType = visit(ctx.getChild(0));

		if (argType.IsArrayType()) {
			ErrorPrinter.printRawString("Built-in print functions doesn't support arrays");
		}

		return null;
	}

	@Override
	public OFPType visitReturnStatement(OfpParser.ReturnStatementContext ctx) {

		OFPType returnExpressionType = visit(ctx.expression());

		Scope scope = currentScope;

		while (!(scope instanceof Function)) {
			scope = scope.getEnclosingScope();
		}

		Function currentFunction = (Function) scope;

		if (!currentFunction.getType().equals(returnExpressionType)) {
			ErrorPrinter.printRawString("Type of return expression" + "(" + returnExpressionType + ")"
					+ " doesn't match function return type" + "(" + currentFunction.getType() + ")");
		}

		return null;
	}

	@Override
	// array or string
	public OFPType visitArrayIndexExpression(OfpParser.ArrayIndexExpressionContext ctx) {

		OFPType indexedType = visit(ctx.getChild(0));

		Boolean isArr = indexedType.IsArrayType();
		Boolean isStr = indexedType.equals(OFPType.stringType);

		if (isArr) {
			return indexedType.FromArrayToPrimitive();
		}

		if (isStr) {
			return OFPType.charType;
		}

		ErrorPrinter.printRawString("Indexer '[]' can be applied only for array types or string");

		return null;
	}

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

	@Override
	public OFPType visitIdentifierExpression(OfpParser.IdentifierExpressionContext ctx) {
		String varName = ctx.IDENTIFIER().getText();
		Symbol identifier = currentScope.resolve(varName);

		if (identifier == null) {
			ErrorPrinter.printRawString("Variable " + varName + "was never declared");
			return null;
		}

		return identifier.getType();
	}

	@Override
	public OFPType visitNewCreatorExpression(OfpParser.NewCreatorExpressionContext ctx) {
		String type = ctx.creator().createdName().arrayType().getText();
		OFPType arrayType = OFPType.getTypeFor(type).FromPrimitiveToArray();

		return arrayType;
	}

	@Override
	public OFPType visitMemberExpression(OfpParser.MemberExpressionContext ctx) {
		String property = ctx.IDENTIFIER().getText();

		if (property.contentEquals("length")) {
			return OFPType.intType;
		}

		return null;
	}
}
