package project;

import java.util.*;

/*
 * This class evaluate mathematical expression.
 */

public class MathematicalEvaluator {
	private Tokenizer token;
	private Stack<Double> value;
	private Stack<String> operator;		// TODO: operator's element could be a FunOp
	
	public MathematicalEvaluator(Tokenizer t) {
		token = t;
		value = new Stack<Double>();
		operator = new Stack<String>();
	}
	
	public double getAnswer() throws TokenException, LexicalErrorException {
//		System.out.println(1);///////////////////////////
		while(token.hasNextToken()) {
			Token t = token.peekNextToken();
//			System.out.println(t);/////////////////////
			if(t.isNumber()) {
				value.push(t.getNumber());
			} else if(t.isString()) {
				value.push(getValue(t.getString()));
			} else if(t.isDelimiter()) {
				if(t.getDelimiter().equals("(")) {
					operator.push(t.getDelimiter());
				} else if(t.getDelimiter().equals(")")) {
					rightParenthesis();
				}
			} else if(t.isOperator()) {
				System.out.println(t + " " + t.getOperator());///////////////////
				anOperator(t.getOperator());
			}
			token.readNextToken();
		}
		while(!operator.isEmpty()) {
			String tmp = operator.pop();
			pushResult(tmp);
		}
		return value.pop();
	}
	
	public double getValue(String s) {
		return SMACmodel.variables.get(s);
	}
	
	// TODO: deal with the problem of priority
	public void pushResult(String tmp) {
		System.out.println(3);///////////////////////////
		double v0 = 0, v1 = 0, ans = 0;
		FunOp op = new FunOp(tmp);
		if(op.arity == 1) {
			v1 = value.pop();
		} else if(op.arity == 2) {
			v1 = value.pop();
			v0 = value.pop();
		}
		ans = op.compute(v0, v1);
		System.out.println(v0 + " " + v1 + " " + ans);///////////////////
		value.push(ans);
	}
	
	public void rightParenthesis() {
		String tmp = operator.pop();
		while(!tmp.equals("(")) {
			pushResult(tmp);
			tmp = operator.pop();
		}
	}
	
	public void anOperator(String name) {
		System.out.println(2);///////////////////////////
		if(operator.isEmpty()) {
			operator.push(name);
			return;
		}
		String tmp;
		FunOp thisOp = new FunOp(name);
		while(!operator.isEmpty()) {
			tmp = operator.peek();
			FunOp newOp = new FunOp(tmp);
			if(newOp.priority < thisOp.priority) break;
			tmp = operator.pop();
			System.out.println(newOp.priority + " " + thisOp.priority + " " + thisOp.name);/////////////////////
			pushResult(tmp);
		}
		operator.push(name);
	}
	
	
}
