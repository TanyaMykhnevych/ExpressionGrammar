package FedirkoMykhnevych.A4;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.Scope;
import FedirkoMykhnevych.A2.Symbol;

public class BytecodeGenerator extends OfpBaseVisitor<Type> {
	private final ParseTreeProperty<Scope> scopes;
	private Function currentScope;
	private final String progName;
	final Map<String, Function> declaredFunctions;
	private final ClassWriter cw;
	private GeneratorAdapter mg;

	private Map<String, Integer> variableIndexMap;

	public BytecodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, String progName) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
		this.progName = progName;
		this.cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);

		this.variableIndexMap = new HashMap<String, Integer>();
	}

	public byte[] getByteArray() {
		return cw.toByteArray();
	}

	@Override
	public Type visitStart(OfpParser.StartContext ctx) {
		cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, progName, null, "java/lang/Object", null);
		Method m = Method.getMethod("void <init> ()");
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
		mg.loadThis();
		mg.invokeConstructor(Type.getType(Object.class), m);
		mg.returnValue();
		mg.endMethod();

		visitChildren(ctx);

		return null;
	}

	@Override
	public Type visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentScope = declaredFunctions.get("main");
		Method main = Method.getMethod("void main (String[])");
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, main, null, null, cw);
		visitChildren(ctx);
		mg.returnValue();
		mg.endMethod();

		return Type.VOID_TYPE;
	}

	@Override
	public Type visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		String functionName = ctx.IDENTIFIER().getText();
		currentScope = declaredFunctions.get(functionName);

		String returnValue = getTypeStringByName(ctx.getChild(0).getText());
		String argumentsTypes = getParamsString(ctx.params().paramsList());
		String argumentTypesString = "(" + argumentsTypes + ")";
		String methodSign = currentScope.getSignature();

		Method method = Method.getMethod(methodSign);
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, method, null, null, cw);

		visit(ctx.functionBody());

		mg.returnValue();
		mg.endMethod();

		return null;
	}

	private String getParamsString(OfpParser.ParamsListContext ctx) {
		if (ctx == null)
			return "";

		return ctx.param().stream().map(x -> visit(x).getClassName()).collect(Collectors.joining(","));
	}

	@Override
	public Type visitParam(OfpParser.ParamContext ctx) {
		// todo: distinguish between local and param
		Type variableType = visit(ctx.type());
		return variableType;
	}

	@Override
	public Type visitReturnStatement(OfpParser.ReturnStatementContext ctx) {
		visitChildren(ctx);
		// mg.loadLocal(currentVariableIndex);

		return null;
	}

	@Override
	public Type visitIntLiteralExpression(OfpParser.IntLiteralExpressionContext ctx) {
		int text = Integer.parseInt(ctx.getText());
		mg.push(new Integer(text));

		return Type.INT_TYPE;
	}

	@Override
	public Type visitFloatLiteralExpression(OfpParser.FloatLiteralExpressionContext ctx) {
		double text = Double.parseDouble(ctx.getText());
		mg.push(new Double(text));
		return Type.DOUBLE_TYPE;
	}

	@Override
	public Type visitBoolLiteralExpression(OfpParser.BoolLiteralExpressionContext ctx) {
		mg.push(new Boolean(Boolean.parseBoolean(ctx.getText())));
		return Type.BOOLEAN_TYPE;
	}

	@Override
	public Type visitStringLiteralExpression(OfpParser.StringLiteralExpressionContext ctx) {
		mg.push(ctx.getText());
		return Type.getType(String.class);
	}

	@Override
	public Type visitCharLiteralExpression(OfpParser.CharLiteralExpressionContext ctx) {
		mg.push(ctx.getText());
		return Type.CHAR_TYPE;
	}

	@Override
	public Type visitGtLtExpression(OfpParser.GtLtExpressionContext ctx) {
		return Type.BOOLEAN_TYPE;
	}

	@Override
	public Type visitGtLtBooleanExpression(OfpParser.GtLtBooleanExpressionContext ctx) {
		return Type.BOOLEAN_TYPE;
	}

	@Override
	public Type visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		String fName = ctx.IDENTIFIER().getText();
		Function fx = declaredFunctions.get(fName);
		String sign = fx.getSignature();

		visitChildren(ctx);

		mg.invokeStatic(Type.getType("L" + progName + ";"), Method.getMethod(sign));

		return null;
	}

	@Override
	public Type visitArguments(OfpParser.ArgumentsContext ctx) {
		if (ctx.expressionList() == null)
			return null;

		visitChildren(ctx.expressionList());

		return null;
	}

	@Override
	public Type visitPrimitiveType(OfpParser.PrimitiveTypeContext ctx) {
		return getTypeByName(ctx.getText());
	}

	@Override
	public Type visitType(OfpParser.TypeContext ctx) {
		Type type = visit(ctx.primitiveType());

		return type;
	}

	@Override
	public Type visitVariableAssignStatement(OfpParser.VariableAssignStatementContext ctx) {
		visit(ctx.expression());
		visit(ctx.IDENTIFIER());

		int varIdx = variableIndexMap.get(ctx.IDENTIFIER().getText());
		mg.storeLocal(varIdx);

		return null;
	}

	@Override
	public Type visitVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		Type variableType = visit(ctx.type());
		String varId = ctx.variableDeclarators().variableDeclarator(0).IDENTIFIER().getText();
		visitChildren(ctx.variableDeclarators());
		int index = currentScope.indexOfLocal(varId);
		mg.storeLocal(index, variableType);

		return null;
	}

	@Override
	public Type visitUnaryMinusExpression(OfpParser.UnaryMinusExpressionContext ctx) {
		return Type.BOOLEAN_TYPE;
	}

	private Type resolveIdentifierType(String variableIdentifier) {
		Symbol s = currentScope.resolve(variableIdentifier);
		return s.getType().getAsmType();
	}

	@Override
	public Type visitIdentifierExpression(OfpParser.IdentifierExpressionContext ctx) {
		String varId = ctx.getText();

		if (currentScope.isParam(varId)) {
			int varIdx = currentScope.indexOfParam(varId);
			mg.loadArg(varIdx);
		} else {
			int varIdx = currentScope.indexOfLocal(varId);
			mg.loadLocal(varIdx);
		}

		return resolveIdentifierType(varId);
	}

	@Override
	public Type visitAddSubExpression(OfpParser.AddSubExpressionContext ctx) {
		Type lhsType = visit(ctx.expression(0));
		Type rhsType = visit(ctx.expression(1));

		String operator = ctx.getChild(1).getText();
		if (operator.contentEquals("+")) {
			mg.math(GeneratorAdapter.ADD, lhsType);
		} else if (operator.contentEquals("-")) {
			mg.math(GeneratorAdapter.SUB, lhsType);
		}

		return lhsType;
	}

	@Override
	public Type visitMulDivExpression(OfpParser.MulDivExpressionContext ctx) {
		Type lhsType = visit(ctx.expression(0));
		Type rhsType = visit(ctx.expression(1));

		String operator = ctx.getChild(1).getText();
		if (operator.contentEquals("*")) {
			mg.math(GeneratorAdapter.MUL, lhsType);
		} else if (operator.contentEquals("/")) {
			mg.math(GeneratorAdapter.DIV, lhsType);
		}

		return lhsType;
	}

	@Override
	public Type visitBracketsExpression(OfpParser.BracketsExpressionContext ctx) {
		return visit(ctx.expression());
	}

	// Char type isnt working
	@Override
	public Type visitBuiltinFunctionCall(OfpParser.BuiltinFunctionCallContext ctx) {
		mg.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));

		Type exprType = visit(ctx.builtintFunctionArgument());

		String type = getTypename(exprType);

		if (ctx.builtinFunction().getText().contentEquals("print"))
			mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void print (" + type + ")"));
		else
			mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (" + type + ")"));

		return null;
	}

	public String getTypeStringByName(String type) {
		if (type.contentEquals("int"))
			return "int";
		else if (type.contentEquals("float"))
			return "float";
		else if (type.contentEquals("char"))
			return "char";
		else if (type.contentEquals("bool"))
			return "boolean";
		else if (type.contentEquals("string"))
			return "java.lang.String";
		if (type.contentEquals("void"))
			return "void";
		else
			throw new RuntimeException("Unkown print type " + type);
	}

	public String getTypename(Type exprType) {
		String type = null;

		if (exprType == Type.INT_TYPE)
			return "int";
		else if (exprType == Type.DOUBLE_TYPE)
			return "double";
		else if (exprType == Type.FLOAT_TYPE)
			return "double";
		else if (exprType == Type.CHAR_TYPE)
			return "char";
		else if (exprType == Type.BOOLEAN_TYPE)
			return "bool";
		else if (exprType.getClassName().contentEquals(Type.getObjectType("a").getClassName()))
			return "java.lang.String";
		else
			throw new RuntimeException("Unkown print type " + type);
	}

	public Type getTypeByName(String type) {
		if (type.contentEquals("int"))
			return Type.INT_TYPE;
		else if (type.contentEquals("float"))
			return Type.DOUBLE_TYPE;
		else if (type.contentEquals("char"))
			return Type.CHAR_TYPE;
		else if (type.contentEquals("bool"))
			return Type.BOOLEAN_TYPE;
		else if (type.contentEquals("string"))
			return Type.getType("java.lang.String");
		else
			throw new RuntimeException("Unkown print type " + type);
	}
}
