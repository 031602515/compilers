package com.compilers.lexer;

public class Word extends Token {

   public Word(String s, int tag) { super(s, tag);}
   
   public String toString() {
	   if (tag == Tag.ID) {
		   return "<id," + lexeme + ">" + "\n";
	   } else {
		   return "<" + lexeme + ">" + "\n";
	   }
   }

   public void printData() {
	   if (tag == Tag.ID) {
		  System.out.println("<id," + lexeme + ">");
	   } else {
		   System.out.println("<" + lexeme + ">");
	   }
   }
   
   public static final Word

      and = new Word( "&&", Tag.AND ),  or = new Word( "||", Tag.OR ),
      eq  = new Word( ":=", Tag.EQ  ),  ne = new Word( "<>", Tag.NE ),
      le  = new Word( "<=", Tag.LE  ),  ge = new Word( ">=", Tag.GE ),

      div = new Word( "DIV", Tag.DIV),  mod = new Word( "mod", Tag.MOD),
      
      minus  = new Word( "minus", Tag.MINUS ),
      True   = new Word( "true",  Tag.TRUE  ),
      False  = new Word( "false", Tag.FALSE ),
      temp   = new Word( "t",     Tag.TEMP  );
  
}
