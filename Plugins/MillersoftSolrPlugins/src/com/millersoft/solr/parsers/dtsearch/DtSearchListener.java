// Generated from DtSearch.g4 by ANTLR 4.5.3

package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DtSearchParser}.
 */
public interface DtSearchListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DtSearchParser#query}.
	 * @param ctx the parse tree
	 */
	void enterQuery(DtSearchParser.QueryContext ctx);
	/**
	 * Exit a parse tree produced by {@link DtSearchParser#query}.
	 * @param ctx the parse tree
	 */
	void exitQuery(DtSearchParser.QueryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code not_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterNot_expr(DtSearchParser.Not_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code not_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitNot_expr(DtSearchParser.Not_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code sub_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterSub_expr(DtSearchParser.Sub_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code sub_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitSub_expr(DtSearchParser.Sub_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code and_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAnd_expr(DtSearchParser.And_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code and_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAnd_expr(DtSearchParser.And_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code w_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterW_expr(DtSearchParser.W_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code w_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitW_expr(DtSearchParser.W_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code field_contains_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterField_contains_expr(DtSearchParser.Field_contains_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code field_contains_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitField_contains_expr(DtSearchParser.Field_contains_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code or_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterOr_expr(DtSearchParser.Or_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code or_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitOr_expr(DtSearchParser.Or_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code phrase_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPhrase_expr(DtSearchParser.Phrase_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code phrase_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPhrase_expr(DtSearchParser.Phrase_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code pre_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterPre_expr(DtSearchParser.Pre_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code pre_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitPre_expr(DtSearchParser.Pre_exprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code regexp_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterRegexp_expr(DtSearchParser.Regexp_exprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code regexp_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitRegexp_expr(DtSearchParser.Regexp_exprContext ctx);
	/**
	 * Enter a parse tree produced by {@link DtSearchParser#regexp}.
	 * @param ctx the parse tree
	 */
	void enterRegexp(DtSearchParser.RegexpContext ctx);
	/**
	 * Exit a parse tree produced by {@link DtSearchParser#regexp}.
	 * @param ctx the parse tree
	 */
	void exitRegexp(DtSearchParser.RegexpContext ctx);
	/**
	 * Enter a parse tree produced by {@link DtSearchParser#phrase}.
	 * @param ctx the parse tree
	 */
	void enterPhrase(DtSearchParser.PhraseContext ctx);
	/**
	 * Exit a parse tree produced by {@link DtSearchParser#phrase}.
	 * @param ctx the parse tree
	 */
	void exitPhrase(DtSearchParser.PhraseContext ctx);
	/**
	 * Enter a parse tree produced by {@link DtSearchParser#number}.
	 * @param ctx the parse tree
	 */
	void enterNumber(DtSearchParser.NumberContext ctx);
	/**
	 * Exit a parse tree produced by {@link DtSearchParser#number}.
	 * @param ctx the parse tree
	 */
	void exitNumber(DtSearchParser.NumberContext ctx);
	/**
	 * Enter a parse tree produced by {@link DtSearchParser#floatingPoint}.
	 * @param ctx the parse tree
	 */
	void enterFloatingPoint(DtSearchParser.FloatingPointContext ctx);
	/**
	 * Exit a parse tree produced by {@link DtSearchParser#floatingPoint}.
	 * @param ctx the parse tree
	 */
	void exitFloatingPoint(DtSearchParser.FloatingPointContext ctx);
}