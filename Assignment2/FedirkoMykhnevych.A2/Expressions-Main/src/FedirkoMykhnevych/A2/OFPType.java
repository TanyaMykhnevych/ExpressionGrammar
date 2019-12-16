package FedirkoMykhnevych.A2;

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
		default:
			return undefinedType;
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
