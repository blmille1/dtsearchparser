package com.millersoft.solr.parsers.dtsearch;

import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.millersoft.solr.parsers.dtsearch.DtSearchParser.And_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Field_contains_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Not_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.NumberContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Or_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.PhraseContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Phrase_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Pre_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.QueryContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.Sub_exprContext;
import com.millersoft.solr.parsers.dtsearch.DtSearchParser.W_exprContext;


//TODO: Make this thing implement the interface so that we don't forget to implement something
public class DtSearchVisitorToString extends DtSearchBaseVisitor<String> {
	@Override
	public String visit(ParseTree tree) {
		return super.visit(tree);
	}
	
	@Override
	public String visitAnd_expr(And_exprContext ctx) {
		return visit(ctx.expr(0)) +" AND " + visit(ctx.expr(1));
	}
	
	@Override
	public String visitNot_expr(Not_exprContext ctx) {
		return "NOT " + visit(ctx.expr());
	}
	
	@Override
	public String visitOr_expr(Or_exprContext ctx) {
		return visit(ctx.expr(0)) + " OR " + visit(ctx.expr(1));
	}
	
	@Override
	public String visitField_contains_expr(Field_contains_exprContext ctx) {
		return ctx.FIELD_NAME().getText() + " CONTAINS " + visit(ctx.expr());
	}
	
	@Override
	public String visitSub_expr(Sub_exprContext ctx) {
		return "(" + visit(ctx.expr()) + ")";
	}
	
	@Override
	public String visitPhrase_expr(Phrase_exprContext ctx) {
		return super.visitPhrase_expr(ctx);
	}

	@Override
	public String visitPhrase(PhraseContext ctx) {
		return ctx.getText();
	}
	
	@Override
	public String visitNumber(NumberContext ctx) {
		return ctx.getText();
	}
	
	@Override
	public String visitPre_expr(Pre_exprContext ctx) {
		return visit(ctx.expr(0))+" PRE/"+ctx.number().getText()+" " + visit(ctx.expr(1));
	}
	
	@Override
	public String visitW_expr(W_exprContext ctx) {
		return visit(ctx.expr(0)) + " W/" + ctx.number().getText() + " " + super.visitW_expr(ctx);
	}
	
	@Override
	public String visitTerminal(TerminalNode node) {
		return " terminal node(" + node.getText()+")";
	}
	
	@Override
	public String visitQuery(QueryContext ctx) {
		return super.visitQuery(ctx);
	}
	
	@Override
	public String visitErrorNode(ErrorNode node) {
		return "[ Error node: " + node.getText() + "]";
	}
	
	@Override
	public String visitChildren(RuleNode ruleNode) {
		return super.visitChildren(ruleNode);
	}
}
