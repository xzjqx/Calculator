package project;

import java.util.*;

/**
 * This class is the model in the MVC architecture.
 * The model performs computations and notifies the view
 * with the result. The model should use your code to
 * evaluate SMAC commands and compute the result
 */
public class SMACmodel extends Observable {
	public static Map<String, Double> variables = new HashMap<String, Double>();
	public final static Set<String> keywords = new HashSet<String>();
	/**
	 * evaluates the input and notify
	 * the view with the result (a String)
	 */
   	public void eval(String input) {
   		////// CHANGE THIS PART //////
   		String r = "> " + input + "\n\n";
   		try {

			Tokenizer t = new Tokenizer(input);
			MathematicalEvaluator me = new MathematicalEvaluator(t);
			r += me.getAnswer();
//			while ( t.hasNextToken() ) {
//				r += t.peekNextToken() + " ";
//				t.readNextToken();
//			}

   		}
   		catch ( Exception e ) {
   			r += "ERROR: " + e.getMessage();
   		}
   		//////////////////////////////
   		// this notify the view and send the result
   		setChanged();
		notifyObservers(r);
   	}
}
