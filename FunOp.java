package project;

import java.util.*;

public class FunOp {
	public String name;
	public int arity;
	public int priority;
	
	// TODO: add all operator
	public FunOp(String op) {
		name = op;
		switch(op) {
		case "+": arity = 2; priority = 1; break;
		case "-": arity = 2; priority = 1; break;
		case "*": arity = 2; priority = 2; break;
		case "/": arity = 2; priority = 2; break;
		case "^": arity = 2; priority = 3; break;
		case "~": arity = 1; priority = 4; break;
		case "sin": arity = 1; priority = 4; break;
		case "cos": arity = 1; priority = 4; break;
		case "tan": arity = 1; priority = 4; break;
		default : arity = 0; priority = 0; break;
		}
	}
	
	// TODO: throw GeneralErrorException
	public double compute(double v0, double v1) {
		double ans = 0;
		switch(name) {
		case "+": ans = v0 + v1; break;
		case "-": ans = v0 - v1; break;
		case "*": ans = v0 * v1; break;
		case "/": ans = v0 / v1; break;
		case "~": ans = 0 - v1; break;
		case "^": ans = Math.pow(v0, v1); break;
		case "sin": ans = Math.sin(v1); break;
		case "cos": ans = Math.cos(v1); break;
		case "tan": ans = Math.tan(v1); break;
		default : ans = 0; break;
		}
		return ans;
	}
}
