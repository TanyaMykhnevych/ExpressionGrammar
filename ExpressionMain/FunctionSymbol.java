package mykhnevych.a2;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends Symbol implements Scope {
	Map<String, Symbol> arguments = new LinkedHashMap<String, Symbol>();
	Scope enclosingScope;

	public FunctionSymbol(String name, OFPType retType, Scope enclosingScope) {
		super(name, retType);
		this.enclosingScope = enclosingScope;
	}

	public Symbol resolve(String name) {
		Symbol s = arguments.get(name);
		if (s != null)
			return s;

		if (getEnclosingScope() != null) {
			return getEnclosingScope().resolve(name);
		}
		return null;
	}

	public void define(Symbol sym) {
		arguments.put(sym.getName(), sym);
		sym.setScope(this);
	}

	public Scope getEnclosingScope() {
		return enclosingScope;
	}

	public String getScopeName() {
		return getName();
	}

	public String toString() {
		return "function" + super.toString() + ":" + arguments.values();
	}
}