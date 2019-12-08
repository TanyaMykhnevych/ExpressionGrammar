package FedirkoMykhenvych.A2;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Function extends Symbol implements Scope{
	
	private LinkedHashMap<String, Symbol> params = new LinkedHashMap<String, Symbol>();
	private Map<String, Symbol> locals = new HashMap<String, Symbol>();
	
	private Scope body;
	
	private String name;

	public Function(String name) {
		super(name, null);
	}

	public Function getEnclosingScope() {
		return null;
	}

	public void define(Symbol sym) {		
	}

	public Symbol resolve(String name) {
		if(params.containsKey(name)) {
			return params.get(name);
		} else if(locals.containsKey(name)) {
			return locals.get(name);
		} else {
			return null;
		}
	}

	@Override
	public String toString() {
		return name;
	}

	@Override
	public String getScopeName() {
		// TODO Auto-generated method stub
		return name;
	}
	
}