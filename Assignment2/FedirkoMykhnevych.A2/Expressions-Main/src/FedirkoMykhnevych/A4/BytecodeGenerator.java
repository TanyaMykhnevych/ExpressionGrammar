package FedirkoMykhnevych.A4;

import java.io.PrintStream;
import java.util.Map;
import java.util.Stack;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OFPType;
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
	private Stack<Label> labelStack; // dirty hack for jump into if/while condition

	public BytecodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, String progName) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
		this.progName = progName;
		this.cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		labelStack = new Stack<Label>();
	}

	public byte[] getByteArray() {
		return cw.toByteArray();
	}

	@Override
	public Type visitStart(OfpParser.StartContext ctx) {
		cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, progName, null, "java/lang/Object", null);
		Method ctor = Method.getMethod("void <init> ()");
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, ctor, null, null, cw);
		mg.loadThis();
		mg.invokeConstructor(Type.getType(Object.class), ctor);
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
		Method method = Method.getMethod(currentScope.getSignature());
		mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, method, null, null, cw);

		visit(ctx.functionBody());
		
		//if (currentScope.getType().equals(OFPType.voidType))
			//mg.returnValue();
		
		mg.endMethod();
		return null;
	}
		
	@Override 
	public Type visitWhileStatement(OfpParser.WhileStatementContext ctx) {
		Label exitWhile = new Label();
		mg.goTo(exitWhile);		
			Label enterWhile = mg.mark();
			visit(ctx.statement());
		mg.mark(exitWhile);
		
		this.labelStack.push(enterWhile);		
		visit(ctx.booleanExpression());	
		
		return null; 
	}
	
	@Override 
	public Type visitIfElseStatement(OfpParser.IfElseStatementContext ctx) { 
		Label enterElse = mg.newLabel();
		Label exitElse = mg.newLabel();
		
		labelStack.push(enterElse);		
		visit(ctx.booleanExpression()); // todo: eqals '=='
		visit(ctx.statement(0));
		//mg.goTo(exitElse);
		
		mg.mark(enterElse);
		visit(ctx.statement(1));
		mg.mark(exitElse);
		
		return null;		
	}
	
	@Override
	public Type visitGtLtBooleanExpression(OfpParser.GtLtBooleanExpressionContext ctx) {
		Type type = visit(ctx.expression(0));
		visit(ctx.expression(1));
		
		int opInstr = TypeUtil.GetBooleanOpInverted(mg, ctx.getChild(1).getText());
		mg.ifCmp(type, opInstr, labelStack.pop()); // for specific type
		
		return Type.BOOLEAN_TYPE;
	}

	@Override
	public Type visitParam(OfpParser.ParamContext ctx) {
		// todo: distinguish between local and param
		Type variableType = visit(ctx.type());
		return variableType;
	}

	@Override
	public Type visitReturnStatement(OfpParser.ReturnStatementContext ctx) {
		if (ctx.expression() != null)
			visit(ctx.expression());
		
		mg.returnValue();
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
		String text = ctx.getText();
		text = text.substring(1, text.length()-1);		
		mg.push(text);
		return Type.getType(String.class);
	}

	@Override
	public Type visitCharLiteralExpression(OfpParser.CharLiteralExpressionContext ctx) {
		char literal = ctx.getText().charAt(1);
		mg.push(literal);
		return Type.CHAR_TYPE;
	}

	
	@Override
	public Type visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		String fName = ctx.IDENTIFIER().getText();
		Function fx = declaredFunctions.get(fName);
		String sign = fx.getSignature();

		visit(ctx.arguments());
		mg.invokeStatic(Type.getType("L" + progName + ";"), Method.getMethod(sign));
		return fx.getType().getAsmType();
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

		int varIdx = this.currentScope.indexOfLocal(ctx.IDENTIFIER().getText());
		mg.storeLocal(varIdx);

		return null;
	}

	@Override
	public Type visitVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		Type variableType = visit(ctx.type());		
		String varId = ctx.IDENTIFIER().getText();
		
		// Dirty hack (no idea how to declare variable without default value)
		if(ctx.variableInitializer() == null) 
			TypeUtil.PushDefaultValueorType(mg, variableType);
		else
			visit(ctx.variableInitializer());
		
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
		
		Type exprType = visit(ctx.expression());
		String type = TypeUtil.getTypeNameForByteCodeEmit(exprType);

		if (ctx.builtinFunction().getText().contentEquals("print"))
			mg.invokeVirtual(
					Type.getType(PrintStream.class), 
					Method.getMethod("void print (" + type + ")"));
		else
			mg.invokeVirtual(
					Type.getType(PrintStream.class), 
					Method.getMethod("void println (" + type + ")"));

		return null;
	}

	public String getTypeStringByName(String type) {
		if (type.contentEquals("int"))
			return "int";
		else if (type.contentEquals("float"))
			return "double";
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
