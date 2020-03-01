package FedirkoMykhnevych.A2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Function extends Symbol implements Scope {

	private Map<String, Symbol> params = new HashMap<String, Symbol>();
	private Map<String, Symbol> locals = new HashMap<String, Symbol>();
	private Map<String, Symbol> initializedVariables = new HashMap<String, Symbol>();

	private int localsCounter = 1;
	private int paramsCounter = 0;

	private Map<String, Integer> localsOrderMap = new HashMap<String, Integer>();
	private Map<String, Integer> paramsOrderMap = new HashMap<String, Integer>();

	private String name;

	public Function(String name, OFPType returnType) {
		super(name, returnType);
		this.name = name;
		if (name.contentEquals("main")) {
			paramsCounter++;
		}
	}

	@Override
	public Function getEnclosingScope() {
		return null;
	}

	@Override
	public void define(Symbol sym) {
		locals.put(sym.getName(), sym);
		localsOrderMap.put(sym.getName(), localsCounter++);
	}

	@Override
	public void initialize(Symbol sym) {
		initializedVariables.put(sym.getName(), sym);
	}

	public String getSignature() {
		return this.getType().getStringyType() + " " + this.getName() + "(" + getParamList() + ")";
	}

	private String getParamList() {
		return params.values().stream().map(x -> x.getType().getStringyType()).collect(Collectors.joining(","));
	}

	public boolean isParam(String name) {
		return params.containsKey(name);
	}

	public int indexOfParam(String name) {
		return paramsOrderMap.get(name);
	}

	public int indexOfLocal(String name) {
		return localsOrderMap.get(name);
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
		paramsOrderMap.put(parameter.getName(), paramsCounter++);
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