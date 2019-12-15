package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Function extends Symbol implements Scope {

	private LinkedHashMap<String, Symbol> params = new LinkedHashMap<String, Symbol>();
	private Map<String, Symbol> locals = new HashMap<String, Symbol>();
	private Map<String, Symbol> initializedVariables = new HashMap<String, Symbol>();

	private String name;

	public Function(String name, OFPType returnType) {
		super(name, returnType);
		this.name = name;
	}

	@Override
	public Function getEnclosingScope() {
		return null;
	}

	@Override
	public void define(Symbol sym) {
		locals.put(sym.getName(), sym);
	}

	@Override
	public void initialize(Symbol sym) {
		initializedVariables.put(sym.getName(), sym);
	}

	@Override
	public Symbol resolve(String name) {
		if (params.containsKey(name)) {
			return params.get(name);
		} else if (locals.containsKey(name)) {
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
		return name;
	}

	public void addParam(Symbol parameter) {
		params.put(parameter.getName(), parameter);
	}

	public List<Symbol> getParams() {
		return new ArrayList<Symbol>(params.values());
	}

	public List<OFPType> getParamTypes() {
		List<Symbol> parameterList = getParams();
		List<OFPType> paramTypes = new ArrayList<OFPType>();
		for (Symbol var : parameterList) {
			paramTypes.add(var.getType());
		}
		return paramTypes;
	}
}