package FedirkoMykhenvych.A2;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeProperty;

import FedirkoMykhnevych.A2.OfpBaseVisitor;
import FedirkoMykhnevych.A2.OfpParser;
import FedirkoMykhnevych.A2.OfpParser.FunctionCallContext;

public class TypeCheckingVisitor extends OfpBaseVisitor<OFPType> {

	private final ParseTreeProperty<Scope> scopes;
	private Scope currentScope;
	private Scope globalScope;
	private Function currentFunction; // For logging purposes
	int errorCount = 0; // Keep track of the total number of errors

	private final OfpParser parser;

	public TypeCheckingVisitor(ParseTreeProperty<Scope> scopes, OfpParser parser) {
		this.scopes = scopes;
		this.parser = parser;
	}

	@Override
	public OFPType visitConditionExpression(OfpParser.ConditionExpressionContext ctx) {
		// OFPType t = visit(ctx.getChild(2));
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitMainFunctionDeclaration(OfpParser.MainFunctionDeclarationContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitParamsList(OfpParser.ParamsListContext ctx) {
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitStart(OfpParser.StartContext ctx) {
		globalScope = currentScope;
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionDeclaration(OfpParser.FunctionDeclarationContext ctx) {
		currentFunction = (Function) currentScope;
		return visitChildren(ctx);
	}

	@Override
	public OFPType visitFunctionCall(OfpParser.FunctionCallContext ctx) {
		OFPType type = visit(ctx.expressionList().getChild(0));
		return visitChildren(ctx);
	}
}
