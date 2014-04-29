package com.soql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import com.soql.parser.SOQLListener;
import com.soql.parser.SOQLParser.BoolExpContext;
import com.soql.parser.SOQLParser.ConditionContext;
import com.soql.parser.SOQLParser.ConditionsContext;
import com.soql.parser.SOQLParser.FieldContext;
import com.soql.parser.SOQLParser.FieldTermContext;
import com.soql.parser.SOQLParser.FieldsContext;
import com.soql.parser.SOQLParser.FuncRefContext;
import com.soql.parser.SOQLParser.LiteralContext;
import com.soql.parser.SOQLParser.NRowsContext;
import com.soql.parser.SOQLParser.ObjContext;
import com.soql.parser.SOQLParser.OrderingContext;
import com.soql.parser.SOQLParser.OrderingTermContext;
import com.soql.parser.SOQLParser.QueryContext;
import com.soql.parser.SOQLParser.SimpleConditionContext;
import com.soql.parser.SOQLParser.SubqueryContext;
import com.soql.parser.SOQLParser.TermContext;
import com.soql.parser.SOQLParser.TermsContext;
import com.soql.parser.SOQLParser.ValueContext;
import com.soql.query.Field;
import com.soql.query.Query;

public class QueryEvaluator implements SOQLListener {
	private Query root;
	private Query current;
	private List<Query> stack = new ArrayList<Query>();
	private Map<Query,Status> queryCaptureFields = new HashMap<Query,Status>();

	private class Status {
		boolean inFunction = false;
		boolean capture = false;
	}
	
	public QueryEvaluator(){
		root = new Query();
		current = root;
		queryCaptureFields.put(current, new Status());
	}
	
	public Query getRoot(){
		return root;
	}
	
	private void push(){
		stack.add(0,current);
		current = new Query();
		queryCaptureFields.put(current, new Status());
	}

	private void pop(){
		Query previous = current;
		current = stack.remove(0);
		current.add(previous);
		queryCaptureFields.remove(previous);
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
	public void enterOrderingTerm(OrderingTermContext ctx) {}

	@Override
	public void exitOrderingTerm(OrderingTermContext ctx) {}

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
	public void enterTerms(TermsContext ctx) {
		queryCaptureFields.get(current).capture = true;
	}

	@Override
	public void exitTerms(TermsContext ctx) {
		queryCaptureFields.get(current).capture = false;
	}

	@Override
	public void enterNRows(NRowsContext ctx) {}

	@Override
	public void exitNRows(NRowsContext ctx) {}

	@Override
	public void enterSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void exitSimpleCondition(SimpleConditionContext ctx) {}

	@Override
	public void enterSubquery(SubqueryContext ctx) {
		push();
	}

	@Override
	public void exitSubquery(SubqueryContext ctx) {
		pop();
	}

	@Override
	public void enterField(FieldContext ctx) {
	}

	@Override
	public void exitField(FieldContext ctx) {
		StringBuilder b = new StringBuilder();
		for (int i=0;i<ctx.getChildCount();i++){
			b.append(ctx.getChild(i));
		}

		Field field = new Field();
		field.setName(b.toString());
		if (queryCaptureFields.get(current).capture){
			if (!queryCaptureFields.get(current).inFunction){
				current.add(field);
			} 
		}
	}

	@Override
	public void enterFuncRef(FuncRefContext ctx) {
		queryCaptureFields.get(current).inFunction = true;
	}

	@Override
	public void exitFuncRef(FuncRefContext ctx) {
		StringBuilder b = new StringBuilder();
		for (int i=0;i<ctx.getChildCount();i++){
			b.append(ctx.getChild(i));
		}

		Field field = new Field();
		field.setName(b.toString());
		if (queryCaptureFields.get(current).capture){
			if (queryCaptureFields.get(current).inFunction){
				current.add(field);
			} 
		}
	}

	@Override
	public void enterConditions(ConditionsContext ctx) {}

	@Override
	public void exitConditions(ConditionsContext ctx) {}

	@Override
	public void enterFieldTerm(FieldTermContext ctx) {}

	@Override
	public void exitFieldTerm(FieldTermContext ctx) {}

	@Override
	public void enterTerm(TermContext ctx) {}

	@Override
	public void exitTerm(TermContext ctx) {}

	@Override
	public void enterValue(ValueContext ctx) {}

	@Override
	public void exitValue(ValueContext ctx) {}

	@Override
	public void enterOrdering(OrderingContext ctx) {}

	@Override
	public void exitOrdering(OrderingContext ctx) {}

	@Override
	public void enterObj(ObjContext ctx) {}

	@Override
	public void exitObj(ObjContext ctx) {
		current.setName(ctx.getText());
	}

	@Override
	public void enterLiteral(LiteralContext ctx) {}

	@Override
	public void exitLiteral(LiteralContext ctx) {}

	@Override
	public void enterFields(FieldsContext ctx) {}

	@Override
	public void exitFields(FieldsContext ctx) {}


}