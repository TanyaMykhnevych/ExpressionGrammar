package FedirkoMykhenvych.A2;

public class Symbol {
	private final String name;
	private final OFPType type;
	private Scope scope;

	public Symbol(String name, OFPType type) {
		this.name = name;
		this.type = type;
	}

	public Scope setScope(Scope scope) {
		return this.scope = scope;
	}

	public Scope getScope() {
		return scope;
	}

	public String getName() {
		return name;
	}

	public OFPType getType() {
		return type;
	}
}