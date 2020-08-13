package FedirkoMykhnevych.A4;

import org.objectweb.asm.Type;
import org.objectweb.asm.commons.GeneratorAdapter;

public final class TypeUtil {
	public static void PushDefaultValueorType(GeneratorAdapter mg, Type t) {
		if (t == Type.INT_TYPE)
			mg.push(new Integer(0));
		
		else if (t == Type.DOUBLE_TYPE)
			mg.push(new Double(0));
		
		else if (t == Type.FLOAT_TYPE)
			mg.push(new Double(0));
		
		else if (t == Type.CHAR_TYPE)
			mg.push('\u0000');
		
		else if (t == Type.BOOLEAN_TYPE)
			mg.push(false);
		
		else if (t.getClassName().contentEquals("java.lang.String"))
			mg.push(new String());
	}
	
	public static String getTypeNameForByteCodeEmit(Type exprType) {		
		if (exprType == Type.INT_TYPE)
			return "int";
		else if (exprType == Type.DOUBLE_TYPE)
			return "double";
		else if (exprType == Type.FLOAT_TYPE)
			return "double";
		else if (exprType == Type.CHAR_TYPE)
			return "char";
		else if (exprType == Type.BOOLEAN_TYPE)
			return "boolean";
		else if (exprType.getClassName().contentEquals("java.lang.String"))
			return "java.lang.String";
		else
			throw new RuntimeException("Unkown print type " + exprType);
	}
}
