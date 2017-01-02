package com.compilers.lexer;

import java.util.List;

public class Token {

	public final int tag;
	public int first;
	public String lexeme;
	public String addr;

	public List<Integer> nextList = null;
	public List<Integer> trueList= null;
	public List<Integer> falseList = null;
	
	public Token(int t) { 
		lexeme = "";
		tag = t; 
	}
	
	public Token(String s, int t) {
		lexeme = s;
		tag = t;
	}
	
	public String toString() {return "<" + (char)tag + ">" + "\n" ;}
	public void printData() {System.out.println( "<" + (char)tag + ">");}

}
