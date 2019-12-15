package FedirkoMykhenvych.A2;

public interface Scope {
	public String getScopeName();

	public Scope getEnclosingScope();

	void initialize(Symbol sym);

	public void define(Symbol sym);

	public Symbol resolve(String name);
}