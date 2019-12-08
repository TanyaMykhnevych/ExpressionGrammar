// Generated from Ofp.g4 by ANTLR 4.4
    // Define output package.
    package FedirkoMykhnevych.A2;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link OfpParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface OfpVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link OfpParser#generalStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitGeneralStatement(@NotNull OfpParser.GeneralStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#returnValue}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitReturnValue(@NotNull OfpParser.ReturnValueContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#builtinFunction}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinFunction(@NotNull OfpParser.BuiltinFunctionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#mainFunctionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMainFunctionDeclaration(@NotNull OfpParser.MainFunctionDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#unarySubstract}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnarySubstract(@NotNull OfpParser.UnarySubstractContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(@NotNull OfpParser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#builtintFunctionArgument}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltintFunctionArgument(@NotNull OfpParser.BuiltintFunctionArgumentContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#paramsList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamsList(@NotNull OfpParser.ParamsListContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#literal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLiteral(@NotNull OfpParser.LiteralContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#variableDeclarator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarator(@NotNull OfpParser.VariableDeclaratorContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(@NotNull OfpParser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(@NotNull OfpParser.StatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#createdName}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreatedName(@NotNull OfpParser.CreatedNameContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#block}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlock(@NotNull OfpParser.BlockContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#arrayInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayInitializer(@NotNull OfpParser.ArrayInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#primitiveType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimitiveType(@NotNull OfpParser.PrimitiveTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#builtinFunctionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBuiltinFunctionCall(@NotNull OfpParser.BuiltinFunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#variableInitializer}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableInitializer(@NotNull OfpParser.VariableInitializerContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#arrayType}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayType(@NotNull OfpParser.ArrayTypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#creator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCreator(@NotNull OfpParser.CreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#conditionExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionExpression(@NotNull OfpParser.ConditionExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#blockStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockStatement(@NotNull OfpParser.BlockStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#expression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpression(@NotNull OfpParser.ExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#start}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStart(@NotNull OfpParser.StartContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#ifStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfStatement(@NotNull OfpParser.IfStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#params}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParams(@NotNull OfpParser.ParamsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#arrayElementAssignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayElementAssignStatement(@NotNull OfpParser.ArrayElementAssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#arrayCreator}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArrayCreator(@NotNull OfpParser.ArrayCreatorContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#booleanExpression}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBooleanExpression(@NotNull OfpParser.BooleanExpressionContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#variableDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclaration(@NotNull OfpParser.VariableDeclarationContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#expressionList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpressionList(@NotNull OfpParser.ExpressionListContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#ifBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfBody(@NotNull OfpParser.IfBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#variableDeclarators}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVariableDeclarators(@NotNull OfpParser.VariableDeclaratorsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#functionCall}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionCall(@NotNull OfpParser.FunctionCallContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#assignStatement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignStatement(@NotNull OfpParser.AssignStatementContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#arguments}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitArguments(@NotNull OfpParser.ArgumentsContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#functionBody}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionBody(@NotNull OfpParser.FunctionBodyContext ctx);
	/**
	 * Visit a parse tree produced by {@link OfpParser#functionDeclaration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFunctionDeclaration(@NotNull OfpParser.FunctionDeclarationContext ctx);
}