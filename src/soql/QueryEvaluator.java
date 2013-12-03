package soql;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

import soql.parser.SOQLListener;
import soql.parser.SOQLParser.BoolExpContext;
import soql.parser.SOQLParser.ConditionContext;
import soql.parser.SOQLParser.ConditionsContext;
import soql.parser.SOQLParser.FieldContext;
import soql.parser.SOQLParser.FieldsContext;
import soql.parser.SOQLParser.LiteralContext;
import soql.parser.SOQLParser.ObjContext;
import soql.parser.SOQLParser.QueryContext;
import soql.parser.SOQLParser.SimpleConditionContext;
import soql.parser.SOQLParser.ValueContext;

public class QueryEvaluator implements SOQLListener {
	private boolean collectFields = false;
	private List<String> fields = new ArrayList<String>();

	
	public List<String> getFields(){
		return fields;
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
		if (collectFields) fields.add(ctx.getText());
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
	
}