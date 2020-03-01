package FedirkoMykhnevych.A2;

import org.objectweb.asm.Type;

public class OFPType {
	public static final OFPType intType = new OFPType("int");
	public static final OFPType intArrType = new OFPType("int[]");
	public static final OFPType charType = new OFPType("char");
	public static final OFPType charArrType = new OFPType("char[]");
	public static final OFPType floatType = new OFPType("float");
	public static final OFPType floatArrType = new OFPType("float[]");
	public static final OFPType boolType = new OFPType("bool");
	public static final OFPType stringType = new OFPType("string");
	public static final OFPType voidType = new OFPType("void");
	public static final OFPType undefinedType = new OFPType("");

	public static OFPType getTypeFor(String typeName) {
		switch (typeName) {
		case "int":
			return intType;
		case "int[]":
			return intArrType;
		case "char":
			return charType;
		case "char[]":
			return charArrType;
		case "float":
			return floatType;
		case "float[]":
			return floatArrType;
		case "bool":
			return boolType;
		case "string":
			return stringType;
		case "void":
			return voidType;
		default:
			return undefinedType;
		}
	}
	
	public Type getAsmType() {
		switch (name) {
		case "int":
			return Type.INT_TYPE;
		case "char":
			return Type.CHAR_TYPE;
		case "float":
			return Type.DOUBLE_TYPE;
		case "bool":
			return Type.BOOLEAN_TYPE;
		case "void":
			return Type.VOID_TYPE;
		case "string":
			return Type.getObjectType("a");
		default:
			return null;
		}
	}

	public String getStringyType() {
		switch (name) {
		case "int":
			return "int";
		case "char":
			return "char";
		case "float":
			return "float";
		case "bool":
			return "boolean";
		case "void":
			return "void";
		case "string":
			return "java.lang.String";
		default:
			return null;
		}
	}
	
	private final String name;

	public boolean IsNumberType() {
		return this.equals(intType)
			|| this.equals(floatType);
	}
	
	public boolean IsArrayType() {
		return this.equals(intArrType)
			|| this.equals(charArrType)
			|| this.equals(floatArrType);
	}
	
	private OFPType(String name) {
		this.name = name;
	}

	public OFPType FromArrayToPrimitive() {
		return new OFPType(this.name.replace("[]", ""));
	}

	public OFPType FromPrimitiveToArray() {
		return new OFPType(this.name + "[]");
	}
	
	public boolean equals(OFPType type) {
		return this.getName().contentEquals(type.getName());
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}
