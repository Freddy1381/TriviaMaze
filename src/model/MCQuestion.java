package model;

import java.io.Serializable;
import java.util.ArrayList;

public class MCQuestion extends Question implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ArrayList<String> myOptions;

	public MCQuestion(final String theType, final String theQuestion, 
					  final String theCorrectAns, final String theHint, 
					  final String theOptions) {
		super(theType, theQuestion, theCorrectAns, theHint);
		// TODO Auto-generated constructor stub
		this.myOptions = super.convertStringToArray(theOptions);
	}
	
	public ArrayList<String> getOptions() {
		return myOptions;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Options: " + myOptions.toString();
	}
	
	public static void main(String[] args) {
		MCQuestion mc = new MCQuestion("Multiple Choice", "What is my name?", 
										"Fred", "This is a hint", 
										"Jeff Fred Brandon Nomad");
		System.out.println(mc.toString());
		System.out.println(mc.getOptions().toString());
	}

}
