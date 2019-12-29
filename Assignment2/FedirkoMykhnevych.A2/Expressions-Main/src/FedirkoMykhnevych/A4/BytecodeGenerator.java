package FedirkoMykhnevych.A4;

import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.Scope;

public class BytecodeGenerator extends OfpBaseVisitor<String> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	final Map<String, Function> declaredFunctions;

	public BytecodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
	}

	public byte[] getByteArray() {
		return new byte[0];
	}
}
