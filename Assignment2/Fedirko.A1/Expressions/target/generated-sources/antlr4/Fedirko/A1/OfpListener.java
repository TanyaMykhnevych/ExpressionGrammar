// Generated from Ofp.g4 by ANTLR 4.7.1
    // Define output package.
    package Fedirko.A1;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link OfpParser}.
 */
public interface OfpListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link OfpParser#start}.
	 * @param ctx the parse tree
	 */
	void enterStart(OfpParser.StartContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#start}.
	 * @param ctx the parse tree
	 */
	void exitStart(OfpParser.StartContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(OfpParser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(OfpParser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#arrType}.
	 * @param ctx the parse tree
	 */
	void enterArrType(OfpParser.ArrTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#arrType}.
	 * @param ctx the parse tree
	 */
	void exitArrType(OfpParser.ArrTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#typeOrArrType}.
	 * @param ctx the parse tree
	 */
	void enterTypeOrArrType(OfpParser.TypeOrArrTypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#typeOrArrType}.
	 * @param ctx the parse tree
	 */
	void exitTypeOrArrType(OfpParser.TypeOrArrTypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(OfpParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(OfpParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(OfpParser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(OfpParser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#varInit}.
	 * @param ctx the parse tree
	 */
	void enterVarInit(OfpParser.VarInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#varInit}.
	 * @param ctx the parse tree
	 */
	void exitVarInit(OfpParser.VarInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#arrInit}.
	 * @param ctx the parse tree
	 */
	void enterArrInit(OfpParser.ArrInitContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#arrInit}.
	 * @param ctx the parse tree
	 */
	void exitArrInit(OfpParser.ArrInitContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(OfpParser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(OfpParser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#funcParams}.
	 * @param ctx the parse tree
	 */
	void enterFuncParams(OfpParser.FuncParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#funcParams}.
	 * @param ctx the parse tree
	 */
	void exitFuncParams(OfpParser.FuncParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#funcParam}.
	 * @param ctx the parse tree
	 */
	void enterFuncParam(OfpParser.FuncParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#funcParam}.
	 * @param ctx the parse tree
	 */
	void exitFuncParam(OfpParser.FuncParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void enterFuncCall(OfpParser.FuncCallContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#funcCall}.
	 * @param ctx the parse tree
	 */
	void exitFuncCall(OfpParser.FuncCallContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#funcArgs}.
	 * @param ctx the parse tree
	 */
	void enterFuncArgs(OfpParser.FuncArgsContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#funcArgs}.
	 * @param ctx the parse tree
	 */
	void exitFuncArgs(OfpParser.FuncArgsContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#assgnStat}.
	 * @param ctx the parse tree
	 */
	void enterAssgnStat(OfpParser.AssgnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#assgnStat}.
	 * @param ctx the parse tree
	 */
	void exitAssgnStat(OfpParser.AssgnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void enterIfStat(OfpParser.IfStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#ifStat}.
	 * @param ctx the parse tree
	 */
	void exitIfStat(OfpParser.IfStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void enterWhileStat(OfpParser.WhileStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#whileStat}.
	 * @param ctx the parse tree
	 */
	void exitWhileStat(OfpParser.WhileStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void enterReturnStat(OfpParser.ReturnStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#returnStat}.
	 * @param ctx the parse tree
	 */
	void exitReturnStat(OfpParser.ReturnStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void enterCodeBlock(OfpParser.CodeBlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#codeBlock}.
	 * @param ctx the parse tree
	 */
	void exitCodeBlock(OfpParser.CodeBlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void enterBlockStat(OfpParser.BlockStatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#blockStat}.
	 * @param ctx the parse tree
	 */
	void exitBlockStat(OfpParser.BlockStatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStat(OfpParser.StatContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStat(OfpParser.StatContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(OfpParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(OfpParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link OfpParser#r}.
	 * @param ctx the parse tree
	 */
	void enterR(OfpParser.RContext ctx);
	/**
	 * Exit a parse tree produced by {@link OfpParser#r}.
	 * @param ctx the parse tree
	 */
	void exitR(OfpParser.RContext ctx);
}