package com.compilers.lexer;

public class Real extends Token {

	public Real(float v) { super(v + "", Tag.REAL);}
	public String toString() { return "<real," + lexeme + ">" + "\n"; }
	public void printData() {
		System.out.println("<real," + lexeme + ">");
	}
}
