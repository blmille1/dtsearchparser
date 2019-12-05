// Generated from DtSearch.g4 by ANTLR 4.5.3

package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DtSearchParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DtSearchVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DtSearchParser#query}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitQuery(DtSearchParser.QueryContext ctx);
	/**
	 * Visit a parse tree produced by the {@code not_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNot_expr(DtSearchParser.Not_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code sub_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSub_expr(DtSearchParser.Sub_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code and_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAnd_expr(DtSearchParser.And_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code w_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitW_expr(DtSearchParser.W_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code field_contains_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitField_contains_expr(DtSearchParser.Field_contains_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code or_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOr_expr(DtSearchParser.Or_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code phrase_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase_expr(DtSearchParser.Phrase_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code pre_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPre_expr(DtSearchParser.Pre_exprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code regexp_expr}
	 * labeled alternative in {@link DtSearchParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexp_expr(DtSearchParser.Regexp_exprContext ctx);
	/**
	 * Visit a parse tree produced by {@link DtSearchParser#regexp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRegexp(DtSearchParser.RegexpContext ctx);
	/**
	 * Visit a parse tree produced by {@link DtSearchParser#phrase}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPhrase(DtSearchParser.PhraseContext ctx);
	/**
	 * Visit a parse tree produced by {@link DtSearchParser#number}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNumber(DtSearchParser.NumberContext ctx);
	/**
	 * Visit a parse tree produced by {@link DtSearchParser#floatingPoint}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFloatingPoint(DtSearchParser.FloatingPointContext ctx);
}