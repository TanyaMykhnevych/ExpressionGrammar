package FedirkoMykhenvych.A2;

public class Symbol {
	private final String name;
	//private final OFPType type;
	private Scope scope; // All symbols know what scope contains them.

	public Symbol(String name, Scope scope) {
		this.name = name;
		this.scope = scope;
	}
	
//	public Symbol(String name, OFPType type, Scope scope) {
//		this.name = name;
//		this.type = type;
//		this.scope = scope;
//	}

	public Scope setScope(Scope scope) {
		return this.scope = scope;
	}
	
	public Scope getScope() {
		return scope;
	}

	public String getName() {
		return name;
	}

//	public OFPType getType() {
//		return type;
// 	}
	
}