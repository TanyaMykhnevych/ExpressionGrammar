package FedirkoMykhnevych.A4;

import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.Opcodes;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.Scope;

public class BytecodeGenerator extends OfpBaseVisitor<Type> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private final String progName;
	final Map<String, Function> declaredFunctions;
	private final ClassWriter cw;
	private GeneratorAdapter mg;
	private int currentVariableIndex = 1;

	public BytecodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, String progName) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
		this.progName = progName;
		this.cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
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
		currentVariableIndex = 1;
		Method main = Method.getMethod("void main (String[])");
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, main, null, null, cw);
		visitChildren(ctx);
		mg.returnValue();
		mg.endMethod();

		return Type.VOID_TYPE;
	}

	@Override
	public Type visitIntLiteralExpression(OfpParser.IntLiteralExpressionContext ctx) {
		mg.push(new Integer(ctx.getText()));
		return Type.INT_TYPE;
	}

	@Override
	public Type visitFloatLiteralExpression(OfpParser.FloatLiteralExpressionContext ctx) {
		mg.push(new Double(ctx.getText()));
		return Type.DOUBLE_TYPE;
	}

	@Override
	public Type visitStringLiteralExpression(OfpParser.StringLiteralExpressionContext ctx) {
		mg.push(ctx.getText());
		return null;
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
		String fName = ctx.getChild(0).getText();
		Function fSymbol = declaredFunctions.get(fName);
		return fSymbol.getType().getType();
	}

	@Override
	public Type visitVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	// DOES NOT WORK
	@Override
	public Type visitVariableDeclarator(OfpParser.VariableDeclaratorContext ctx) {
		Type type = visit(ctx.getChild(1));
		mg.storeLocal(currentVariableIndex++, type);
		return null;
	}

	@Override
	public Type visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentVariableIndex = 1;

		String name = ctx.IDENTIFIER().getText();
		String returnValue = ""; // get return value here
		String argumentTypesString = ""; // get argument types here , ex (int, int)

		Method method = Method.getMethod(returnValue + " " + name + " " + argumentTypesString);
		mg = new GeneratorAdapter(Opcodes.ACC_PRIVATE + Opcodes.ACC_STATIC, method, null, null, cw);

		// load arguments, method body

		mg.returnValue();
		mg.endMethod();

		return null;
	}

	@Override
	public Type visitUnaryMinusExpression(OfpParser.UnaryMinusExpressionContext ctx) {
		return Type.BOOLEAN_TYPE;
	}

	@Override
	public Type visitAddSubExpression(OfpParser.AddSubExpressionContext ctx) {
		Type lhsType = visit(ctx.expression(0));
		Type rhsType = visit(ctx.expression(2));

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
		Type rhsType = visit(ctx.expression(2));

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

	// Copied from lecture
	/*@Override
	public Type visitBuiltinFunctionCall(OfpParser.BuiltinFunctionCallContext ctx) {
		mg.getStatic(Type.getType(System.class), "out", Type.getType(PrintStream.class));
		Type eType = visit(ctx.getChild(2)); // Push expr, return ASM expr type
		String type = null; // Select print type
		if (eType == Type.INT_TYPE)
			type = "int";
		else if (eType == Type.DOUBLE_TYPE)
			type = "double";
		else if (eType == Type.CHAR_TYPE)
			type = "char";
		else if (eType == Type.BOOLEAN_TYPE)
			type = "boolean";
		else if (eType.toString().equals("java.lang.String"))
			type = "java.lang.String";
		else
			throw new RuntimeException("Unkown print type " + eType);
		if (ctx.getChild(0).getText().equals("println"))
			mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void println (" + type + ")"));
		else
			mg.invokeVirtual(Type.getType(PrintStream.class), Method.getMethod("void print (" + type + ")"));
		return null;
	}*/
}
