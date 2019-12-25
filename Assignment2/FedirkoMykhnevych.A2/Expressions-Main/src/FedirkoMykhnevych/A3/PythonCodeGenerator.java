package FedirkoMykhnevych.A3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

import FedirkoMykhnevych.A2.ErrorPrinter;
import FedirkoMykhnevych.A2.Function;
import FedirkoMykhnevych.A2.OFPType;
import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.Scope;

public class PythonCodeGenerator extends OfpBaseVisitor<String> {
	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	final Map<String, Function> declaredFunctions;

	private int depth = 0; // The current depth/indentation level
	private HashMap<Integer, String> indentCache = new HashMap<Integer, String>();

	private String indent(int indentLevel) {
		String ind = indentCache.get(indentLevel);
		if (ind == null) {
			ind = "";
			for (int i = 0; i < indentLevel; i++)
				ind += "    "; // four space chars
			indentCache.put(indentLevel, ind);
		}
		return ind;
	}

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
		if (b.contentEquals("true"))
			return "True";
		else
			return "False";
	}

	@Override
	public String visitBooleanLiteralExpression(OfpParser.BooleanLiteralExpressionContext ctx) {
		String b = ctx.getChild(0).getText();
		if (b.contentEquals("true"))
			return "True";
		else
			return "False";
	}

	@Override
	public String visitIntLiteralExpression(OfpParser.IntLiteralExpressionContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitCharLiteralExpression(OfpParser.CharLiteralExpressionContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitStringLiteralExpression(OfpParser.StringLiteralExpressionContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitFloatLiteralExpression(OfpParser.FloatLiteralExpressionContext ctx) {
		return ctx.getText();
	}

	@Override
	public String visitArrayInitializer(OfpParser.ArrayInitializerContext ctx) {
		StringBuilder buf = new StringBuilder();
		buf.append(visit(ctx.getChild(1)));
		for (int i = 3; i < ctx.getChildCount(); i += 2) {
			buf.append(", ").append(visit(ctx.getChild(i)));
		}
		return "[" + buf.toString() + "]";
	}

	@Override
	public String visitFunctionBody(OfpParser.FunctionBodyContext ctx) {
		currentScope = scopes.get(ctx);
		String buf = visitChildren(ctx);
		return buf;
	}

	@Override
	public String visitBlock(OfpParser.BlockContext ctx) {
		StringBuilder buf = new StringBuilder();
		for (int i = 1; i < ctx.getChildCount() - 1; i++) { // skip brackets
			String stmt = visit(ctx.getChild(i));
			buf.append(stmt);
		}
		if (buf.length() == 0) // see Issue 3
			buf.append(indent(depth)).append("pass").append("\n");
		return buf.toString();
	}

	@Override
	public String visitBlockStatement(OfpParser.BlockStatementContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public String visitBracketsExpression(OfpParser.BracketsExpressionContext ctx) {
		return "(" + visit(ctx.expression()) + ")";
	}
	
	
	@Override
	public String visitStatement(OfpParser.StatementContext ctx) {
		return ctx.getChild(0).getText().contentEquals(";") ? "" : visit(ctx.getChild(0));
	}

	@Override
	public String visitGeneralStatement(OfpParser.GeneralStatementContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public String visitWhileStatement(OfpParser.WhileStatementContext ctx) {
		String whilefCondition = "while " + visit(ctx.getChild(1)) + ":\n";
		depth++;
		String whileBody = visit(ctx.getChild(2));
		depth--;
		return indent(depth) + whilefCondition + whileBody;
	}

	@Override
	public String visitConditionExpression(OfpParser.ConditionExpressionContext ctx) {
		return visit(ctx.getChild(1));
	}

	@Override
	public String visitGtLtBooleanExpression(OfpParser.GtLtBooleanExpressionContext ctx) {
		return visit(ctx.expression(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expression(1));
	}
	
	@Override
	public String visitGtLtExpression(OfpParser.GtLtExpressionContext ctx) {
		return visit(ctx.expression(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.expression(1));
	}


	@Override
	public String visitBooleanEqualsExpression(OfpParser.BooleanEqualsExpressionContext ctx) {
		return visit(ctx.expression(0)) + " == " + visit(ctx.expression(1));
	}


	public String visitEqualsExpression(OfpParser.EqualsExpressionContext ctx) {
		return visit(ctx.expression(0)) + " == " + visit(ctx.expression(1));
	}
	
	@Override
	public String visitIdentifierBooleanExpression(OfpParser.IdentifierBooleanExpressionContext ctx) {
		return ctx.IDENTIFIER().getText();
	}

	@Override
	public String visitFunctionCallBooleanExpression(OfpParser.FunctionCallBooleanExpressionContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public String visitIfStatement(OfpParser.IfStatementContext ctx) {
		String ifCondition = "if " + visit(ctx.getChild(1)) + ":\n";
		depth++;
		String ifBody = visit(ctx.getChild(2));
		depth--;
		String elseBody = ctx.getChild(3) != null ? visit(ctx.getChild(3)) : "";
		return indent(depth) + ifCondition + ifBody + elseBody;

	}

	@Override
	public String visitIfBody(OfpParser.IfBodyContext ctx) {
		return visit(ctx.getChild(1));
	}

	@Override
	public String visitElseStatement(OfpParser.ElseStatementContext ctx) {
		String result = "else:\n";
		depth++;
		result += visit(ctx.getChild(1));
		depth--;

		return result;
	}

	@Override
	public String visitIdentifierExpression(OfpParser.IdentifierExpressionContext ctx) {
		return getSafePythonId(ctx.IDENTIFIER().getText());
	}

	@Override
	public String visitVariableDeclaration(OfpParser.VariableDeclarationContext ctx) {
		ParseTree variableDeclarators = ctx.getChild(1);
		ParseTree variableDeclarator = variableDeclarators.getChild(0);
		if (variableDeclarator.getChildCount() > 2) {
			String id = variableDeclarator.getChild(0).getText();
			String expr = "";
			expr = visit(ctx.getChild(1));
			return indent(depth) + getSafePythonId(id) + " = " + expr + "\n";
		} // actually, it’s a bit more complicated, see Issue 5
		else // ignore pure variable declarations
			return ""; // see the next slide!!!
	}

	@Override 
	public String visitArrayElementAssignStatement(OfpParser.ArrayElementAssignStatementContext ctx) { 
		String variableId = ctx.IDENTIFIER().getText();
		String indexerExpr = visit(ctx.expression(0));
		
		String rhExpr = visit(ctx.expression(1));
		
		return indent(depth) + getSafePythonId(variableId) + "[" + indexerExpr + "]" + " = " + rhExpr + "\n";			
	}
	
	@Override 
	public String visitVariableAssignStatement(OfpParser.VariableAssignStatementContext ctx) { 
		String variableId = ctx.IDENTIFIER().getText();		
		String expr = "";
		expr = visit(ctx.expression());

		return indent(depth) + getSafePythonId(variableId) + " = " + expr + "\n";
	}

	@Override
	public String visitReturnStatement(OfpParser.ReturnStatementContext ctx) {
		if(ctx.expression() == null) {
			return indent(depth) + "return";
		}
		
		String returnExpression = visit(ctx.expression());
		return indent(depth) + "return " + returnExpression + "\n";
	}

	@Override
	public String visitAddSubExpression(OfpParser.AddSubExpressionContext ctx) {
		return visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
	}
	
	@Override
	public String visitMulDivExpression(OfpParser.MulDivExpressionContext ctx) {
		return  visit(ctx.getChild(0)) + " " + ctx.getChild(1).getText() + " " + visit(ctx.getChild(2));
	}

	@Override
	public String visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		currentScope = scopes.get(ctx);
		String start = "#\n# Program entry point - main\n#\n";
		depth = 0; // no indentation expected here
		String body = visit(ctx.getChild(1));
		return start + body;
	}

	@Override
	public String visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentScope = scopes.get(ctx);
		depth = 0; // no indentation expected here
		String signature = "def " + getSafePythonId(ctx.IDENTIFIER().getText());
		String paramters = visit(ctx.getChild(2));
		depth++;
		String body = visit(ctx.getChild(3));
		depth--;
		return signature + paramters + "\n" + body;
	}

	@Override
	public String visitParams(OfpParser.ParamsContext ctx) {
		String paramsList = "";
		if (ctx.getChildCount() > 2) {
			paramsList = visit(ctx.getChild(1));
		}
		return "(" + paramsList + "):";
	}

	@Override
	public String visitParamsList(OfpParser.ParamsListContext ctx) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < ctx.getChildCount(); i += 2) {
			String p = visit(ctx.getChild(i));
			if (i != ctx.getChildCount() - 1) {
				p += ", ";
			}
			buf.append(p);
		}
		return buf.toString();
	}

	@Override
	public String visitParam(OfpParser.ParamContext ctx) {
		return getSafePythonId(ctx.getChild(1).getText());
	}

	@Override
	public String visitFunctionCall(OfpParser.FunctionCallContext ctx) {		
		String funcName = getSafePythonId(ctx.IDENTIFIER().getText());
		String args = visit(ctx.arguments());
		
		return funcName + "(" + args + ")";
	}

	@Override
	public String visitArguments(OfpParser.ArgumentsContext ctx) {		
		if(ctx.expressionList() == null)
			return "";
		
		String args = visit(ctx.expressionList());
		
		return args;
	}

	@Override
	public String visitExpressionList(OfpParser.ExpressionListContext ctx) {
		StringBuilder buf = new StringBuilder();
		for (int i = 0; i < ctx.getChildCount(); i += 2) {
			ParserRuleContext p = (ParserRuleContext) ctx.getChild(i);
			buf.append(visit(p));
			if (i != ctx.getChildCount() - 1) {
				buf.append(", ");
			}
		}
		return buf.toString();
	}

	@Override
	public String visitBuiltinFunctionCall(OfpParser.BuiltinFunctionCallContext ctx) {
		return indent(depth) + "print" + "(" + visit(ctx.getChild(2)) + ")\n";
	}

	@Override
	public String visitBuiltinFunction(OfpParser.BuiltinFunctionContext ctx) {
		return ctx.getChild(0).getText();
	}

	@Override
	public String visitBuiltintFunctionArgument(OfpParser.BuiltintFunctionArgumentContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public String visitNewCreatorExpression(OfpParser.NewCreatorExpressionContext ctx) {
		return "[]";
	}

	@Override
	public String visitVariableInitializer(OfpParser.VariableInitializerContext ctx) {
		return visit(ctx.getChild(0));
	}

	@Override
	public String visitArrayIndexExpression(OfpParser.ArrayIndexExpressionContext ctx) {
		return visit(ctx.getChild(0)) + '[' + visit(ctx.getChild(2)) + ']';
	}

	@Override
	public String visitStart(OfpParser.StartContext ctx) {
		StringBuilder buf = new StringBuilder();

		ParserRuleContext main = null;
		for (int i = 0; i < ctx.getChildCount(); i++) {
			ParserRuleContext p = (ParserRuleContext) ctx.getChild(i);

			if (p instanceof OfpParser.MainFunctionDeclarationContext) {
				main = p;
			} else {
				buf.append(visit(p));
			}
		}
		buf.append(visit(main));
		return buf.toString();
	}
	
	@Override
	public String visitMemberExpression(OfpParser.MemberExpressionContext ctx) {
		String property = ctx.IDENTIFIER().getText();

		if (!property.contentEquals("length")) {
			return null;
		}
		
		String member = visit(ctx.expression());

		return "len(" + member + ")";
	}
}
