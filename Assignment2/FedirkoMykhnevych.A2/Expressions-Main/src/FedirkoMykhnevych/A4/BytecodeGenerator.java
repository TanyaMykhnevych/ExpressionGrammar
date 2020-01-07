package FedirkoMykhnevych.A4;

import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;
import org.objectweb.asm.Opcodes;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OFPType;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.Scope;

public class BytecodeGenerator extends OfpBaseVisitor<OFPType> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private final String progName;
	final Map<String, Function> declaredFunctions;
	private final ClassWriter cw;
	private final GeneratorAdapter mg;

	public BytecodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes, String progName) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
		this.progName = progName;
		this.cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		this.mg = new GeneratorAdapter(Opcodes.ACC_PUBLIC, m, null, null, cw);
	}

	public byte[] getByteArray() {
		return cw.toByteArray();
	}

	@Override
	public OFPType visitStart(OfpParser.StartContext ctx) {
		cw.visit(Opcodes.V1_1, Opcodes.ACC_PUBLIC, progName, null, "java/lang/Object", null);
		Method m = Method.getMethod("void <init> ()");
		mg.loadThis();
		mg.invokeConstructor(Type.getType(Object.class), m);
		mg.returnValue();
		mg.endMethod();

		return null;
	}

	@Override
	public OFPType visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		Method main = Method.getMethod("void main (String[])");
		visitChildren(ctx);
		mg.returnValue();
		mg.endMethod();

		return null;
	}
}
