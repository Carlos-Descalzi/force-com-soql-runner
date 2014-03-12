package com.soql;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.soql.parser.SOQLListener;
import com.soql.parser.SOQLParser.BoolExpContext;
import com.soql.parser.SOQLParser.ConditionContext;
import com.soql.parser.SOQLParser.ConditionsContext;
import com.soql.parser.SOQLParser.FieldContext;
import com.soql.parser.SOQLParser.FieldsContext;
import com.soql.parser.SOQLParser.LiteralContext;
import com.soql.parser.SOQLParser.ObjContext;
import com.soql.parser.SOQLParser.QueryContext;
import com.soql.parser.SOQLParser.SimpleConditionContext;
import com.soql.parser.SOQLParser.SubqueryContext;
import com.soql.parser.SOQLParser.TermContext;
import com.soql.parser.SOQLParser.ValueContext;
import com.soql.query.QueryField;
import com.soql.query.QueryObject;

public class QueryEvaluator implements SOQLListener {
	private boolean collectFields = false;
	private QueryObject root = new QueryObject();
	private QueryObject current;
	private List<QueryObject> path = new ArrayList<QueryObject>();
	
	public QueryEvaluator(){
		current = root;
	}
	
	public QueryObject getRoot(){
		return root;
	}
	
	@Override
	public void enterEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitTerminal(TerminalNode arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterConditions(ConditionsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitConditions(ConditionsContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterField(FieldContext ctx) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitField(FieldContext ctx) {
		if (collectFields) current.getTerms().add(new QueryField(ctx.getText()));
	}

	@Override
	public void enterBoolExp(BoolExpContext ctx) {
	}

	@Override
	public void exitBoolExp(BoolExpContext ctx) {
	}

	@Override
	public void enterCondition(ConditionContext ctx) {
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
	}

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
	public void exitObj(ObjContext ctx) {}

	@Override
	public void enterSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void exitSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void enterLiteral(LiteralContext ctx) {}

	@Override
	public void exitLiteral(LiteralContext ctx) {}

	@Override
	public void enterFields(FieldsContext ctx) {
		collectFields = true;
	}

	@Override
	public void exitFields(FieldsContext ctx) {
		collectFields = false;
	}
	@Override
	public void enterTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void exitTerm(TermContext ctx) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void enterSubquery(SubqueryContext ctx) {
		path.add(current);
		current = new QueryObject();
	}
	@Override
	public void exitSubquery(SubqueryContext ctx) {
		QueryObject parent = path.remove(path.size()-1);
		parent.getTerms().add(current);
		current = parent;
	}
	
}