package com.soql;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.apache.commons.lang.StringUtils;

import com.soql.parser.SOQLListener;
import com.soql.parser.SOQLParser.BoolExpContext;
import com.soql.parser.SOQLParser.ConditionContext;
import com.soql.parser.SOQLParser.ConditionsContext;
import com.soql.parser.SOQLParser.FieldContext;
import com.soql.parser.SOQLParser.FieldTermContext;
import com.soql.parser.SOQLParser.FieldsContext;
import com.soql.parser.SOQLParser.FuncRefContext;
import com.soql.parser.SOQLParser.LiteralContext;
import com.soql.parser.SOQLParser.ObjContext;
import com.soql.parser.SOQLParser.QueryContext;
import com.soql.parser.SOQLParser.SimpleConditionContext;
import com.soql.parser.SOQLParser.SubqueryContext;
import com.soql.parser.SOQLParser.TermContext;
import com.soql.parser.SOQLParser.TermsContext;
import com.soql.parser.SOQLParser.ValueContext;
import com.soql.query.FieldTerm;
import com.soql.query.Function;
import com.soql.query.QueryField;
import com.soql.query.QueryObject;
import com.soql.query.Term;

public class QueryEvaluator implements SOQLListener {
	private boolean collectFields = false;
	private QueryObject root = new QueryObject();
	private Term current;
	private List<Term> path = new ArrayList<Term>();
	
	public QueryEvaluator(){
		current = root;
	}
	
	public QueryObject getRoot(){
		return root;
	}
	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {}

	@Override
	public void visitErrorNode(ErrorNode arg0) {}

	@Override
	public void visitTerminal(TerminalNode arg0) {}

	@Override
	public void enterConditions(ConditionsContext ctx) {}

	@Override
	public void exitConditions(ConditionsContext ctx) {}

	@Override
	public void enterFuncRef(FuncRefContext ctx) {
		current = new Function();
	}

	@Override
	public void exitFuncRef(FuncRefContext ctx) {
		((Function)current).setName(ctx.ID().getText());
		String txt = ctx.getText();
		
		System.out.println(txt);
		
	}

	@Override
	public void enterFieldTerm(FieldTermContext ctx) {
	}

	@Override
	public void exitFieldTerm(FieldTermContext ctx) {
		if (collectFields){
			((FieldTerm)current).setAlias(ctx.ALIAS() != null ? ctx.ALIAS().getText() : null);
		}
	}

	@Override
	public void enterField(FieldContext ctx) {
		if (collectFields){
			current = new QueryField();
		}
	}
	
	@Override
	public void exitField(FieldContext ctx) {
		if (collectFields){
			((QueryField)current).setName(ctx.getText());
		}
	}

	@Override
	public void enterBoolExp(BoolExpContext ctx) {}

	@Override
	public void exitBoolExp(BoolExpContext ctx) {}

	@Override
	public void enterCondition(ConditionContext ctx) {}

	@Override
	public void exitCondition(ConditionContext ctx) {}

	@Override
	public void enterQuery(QueryContext ctx) {}

	@Override
	public void exitQuery(QueryContext ctx) {}

	@Override
	public void enterValue(ValueContext ctx) {}

	@Override
	public void exitValue(ValueContext ctx) {}

	@Override
	public void enterObj(ObjContext ctx) {}

	@Override
	public void exitObj(ObjContext ctx) {
		if (current instanceof QueryObject){
			((QueryObject)current).setName(ctx.getText());
		}
	}
	@Override
	public void exitTerm(TermContext ctx) {
		Term current = this.current;
		this.current = path.remove(path.size()-1);
		((QueryObject)this.current).getTerms().add(current);
	}
	@Override
	public void enterTerm(TermContext ctx) {
		path.add(current);
		current = null;
	}
	@Override
	public void enterTerms(TermsContext ctx) {
		collectFields = true;
		
	}

	@Override
	public void exitTerms(TermsContext ctx) {
		collectFields = false;
	}

	
	@Override
	public void enterSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void exitSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void enterLiteral(LiteralContext ctx) {}

	@Override
	public void exitLiteral(LiteralContext ctx) {}

	@Override
	public void enterFields(FieldsContext ctx) {}

	@Override
	public void exitFields(FieldsContext ctx) {}
	
	
	@Override
	public void enterSubquery(SubqueryContext ctx) {
		path.add(current);
		current = new QueryObject();
	}
	@Override
	public void exitSubquery(SubqueryContext ctx) {
		QueryObject parent = (QueryObject)path.remove(path.size()-1);
		parent.getTerms().add(current);
		current = parent;
	}
	

}