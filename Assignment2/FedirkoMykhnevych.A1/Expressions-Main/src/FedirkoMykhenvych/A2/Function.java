package FedirkoMykhenvych.A2;

import java.util.LinkedHashMap;
import java.util.Map;

public class Function implements Scope{
	private String name;

	public Function(String name) {
		this.name = name;
	}

	public Function getEnclosingScope() {
		return null;
	}

	public void define(Symbol sym) {		
	}

	public Symbol resolve(String name) {
		return null;
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