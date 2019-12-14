package FedirkoMykhenvych.A2;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope implements Scope {
	private Scope enclosingScope;
	private Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

	public BaseScope(Scope enclosingScope) {
		this.enclosingScope = enclosingScope;
	}

	public Scope getEnclosingScope() {
		return enclosingScope;
	}

	public void define(Symbol sym) {
		symbols.put(sym.getName(), sym);
	}

	public Symbol resolve(String name) {
		Symbol s = symbols.get(name);
		if (s != null) {
			return s;
		}

		Scope parent = getEnclosingScope();
		if (parent != null)
			return parent.resolve(name);
		return null;
	}

	@Override
	public String toString() {
		return symbols.keySet().toString();
	}

	//@Override
	public String getScopeName() {
		// TODO Auto-generated method stub
		return enclosingScope.getScopeName();
	}

	@Override
	public void initialize(Symbol sym) {
		// TODO Auto-generated method stub
		
	}
}
