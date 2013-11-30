// Generated from /home/carlos/Proyectos/force-com-soql-runner/SOQL.g4 by ANTLR 4.1
package soql.parser;
import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link SOQLParser}.
 */
public interface SOQLListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link SOQLParser#conditions}.
	 * @param ctx the parse tree
	 */
	void enterConditions(@NotNull SOQLParser.ConditionsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#conditions}.
	 * @param ctx the parse tree
	 */
	void exitConditions(@NotNull SOQLParser.ConditionsContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#field}.
	 * @param ctx the parse tree
	 */
	void enterField(@NotNull SOQLParser.FieldContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#field}.
	 * @param ctx the parse tree
	 */
	void exitField(@NotNull SOQLParser.FieldContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void enterBoolExp(@NotNull SOQLParser.BoolExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#boolExp}.
	 * @param ctx the parse tree
	 */
	void exitBoolExp(@NotNull SOQLParser.BoolExpContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void enterCondition(@NotNull SOQLParser.ConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#condition}.
	 * @param ctx the parse tree
	 */
	void exitCondition(@NotNull SOQLParser.ConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(@NotNull SOQLParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(@NotNull SOQLParser.QueryContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#value}.
	 * @param ctx the parse tree
	 */
	void enterValue(@NotNull SOQLParser.ValueContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#value}.
	 * @param ctx the parse tree
	 */
	void exitValue(@NotNull SOQLParser.ValueContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#obj}.
	 * @param ctx the parse tree
	 */
	void enterObj(@NotNull SOQLParser.ObjContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#obj}.
	 * @param ctx the parse tree
	 */
	void exitObj(@NotNull SOQLParser.ObjContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#simpleCondition}.
	 * @param ctx the parse tree
	 */
	void enterSimpleCondition(@NotNull SOQLParser.SimpleConditionContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#simpleCondition}.
	 * @param ctx the parse tree
	 */
	void exitSimpleCondition(@NotNull SOQLParser.SimpleConditionContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(@NotNull SOQLParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(@NotNull SOQLParser.LiteralContext ctx);

	/**
	 * Enter a parse tree produced by {@link SOQLParser#fields}.
	 * @param ctx the parse tree
	 */
	void enterFields(@NotNull SOQLParser.FieldsContext ctx);
	/**
	 * Exit a parse tree produced by {@link SOQLParser#fields}.
	 * @param ctx the parse tree
	 */
	void exitFields(@NotNull SOQLParser.FieldsContext ctx);
}