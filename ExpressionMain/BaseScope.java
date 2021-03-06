package mykhnevych.a2;

import java.util.LinkedHashMap;
import java.util.Map;

public class BaseScope {
	private BaseScope enclosingScope;
	private Map<String, Symbol> symbols = new LinkedHashMap<String, Symbol>();

	public BaseScope(BaseScope enclosingScope) {
		this.enclosingScope = enclosingScope;
	}

	public BaseScope getEnclosingScope() {
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

		BaseScope parent = getEnclosingScope();
		if (parent != null)
			return parent.resolve(name);
		return null;
	}

	@Override
	public String toString() {
		return symbols.keySet().toString();
	}
}
