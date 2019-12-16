package FedirkoMykhnevych.A3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.Scope;

public class PythonCodeGenerator extends OfpBaseVisitor<String> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private Function currentFunction;
	final Map<String, Function> declaredFunctions;

	private int depth = 0;
	// private HashMap<Integer,String> indentCache = ...
	// private String indent(int indentLevel) { ... }

	private static HashSet<String> reservedIds = new HashSet<String>(Arrays.asList("ArithmeticError", "AssertionError",
			"AttributeError", "BaseException", "BlockingIOError", "BrokenPipeError", "BufferError", "BytesWarning",
			"ChildProcessError", "ConnectionAbortedError", "ConnectionError", "ConnectionRefusedError",
			"ConnectionResetError", "DeprecationWarning", "EOFError", "Ellipsis", "EnvironmentError", "Exception",
			"False", "FileExistsError", "FileNotFoundError", "FloatingPointError", "FutureWarning", "GeneratorExit",
			"IOError", "ImportError", "ImportWarning", "IndentationError", "IndexError", "InterruptedError",
			"IsADirectoryError", "KeyError", "KeyboardInterrupt", "LookupError", "MemoryError", "NameError", "None",
			"NotADirectoryError", "NotImplemented", "NotImplementedError", "OSError", "OverflowError",
			"PendingDeprecationWarning", "PermissionError", "ProcessLookupError", "RecursionError", "ReferenceError",
			"ResourceWarning", "RuntimeError", "RuntimeWarning", "StopAsyncIteration", "StopIteration", "SyntaxError",
			"SyntaxWarning", "SystemError", "SystemExit", "TabError", "TimeoutError", "True", "TypeError",
			"UnboundLocalError", "UnicodeDecodeError", "UnicodeEncodeError", "UnicodeError", "UnicodeTranslateError",
			"UnicodeWarning", "UserWarning", "ValueError", "Warning", "ZeroDivisionError", "__build_class__",
			"__debug__", "__doc__", "__import__", "__loader__", "__name__", "__package__", "__spec__", "abs", "all",
			"any", "ascii", "bin", "bool", "bytearray", "bytes", "callable", "chr", "classmethod", "compile", "complex",
			"copyright", "credits", "delattr", "dict", "dir", "divmod", "enumerate", "eval", "exec", "exit", "filter",
			"float", "format", "frozenset", "getattr", "globals", "hasattr", "hash", "help", "hex", "id", "input",
			"int", "isinstance", "issubclass", "iter", "len", "license", "list", "locals", "map", "max", "memoryview",
			"min", "next", "object", "oct", "open", "ord", "pow", "print", "property", "quit", "range", "repr",
			"reversed", "round", "set", "setattr", "slice", "sorted", "staticmethod", "str", "sum", "super", "tuple",
			"type", "vars", "zip"));

	// Replace an OFP identifier if it belongs to the set above
	private static String getSafePythonId(String id) {
		if (reservedIds.contains(id))
			return "ofp_" + id;
		else
			return id;
	}

	public PythonCodeGenerator(final Map<String, Function> functions, ParseTreeProperty<Scope> scopes) {
		this.scopes = scopes;
		this.declaredFunctions = functions;
	}

	@Override
	public String visitBoolLiteralExpression(OfpParser.BoolLiteralExpressionContext ctx) {
		String b = ctx.getChild(0).getText();
		if (b.equals("true"))
			return "True";
		else
			return "False";
	}

	@Override
	public String visitBooleanLiteralExpression(OfpParser.BooleanLiteralExpressionContext ctx) {
		String b = ctx.getChild(0).getText();
		if (b.equals("true"))
			return "True";
		else
			return "False";
	}
}
