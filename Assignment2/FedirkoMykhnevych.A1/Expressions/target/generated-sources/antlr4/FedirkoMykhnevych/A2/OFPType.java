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

	private OFPType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}
