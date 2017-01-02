package com.compilers.lexer;

import java.util.HashMap;
import java.util.Map;

public class Tag {

   public static final int
   
      EQ    = 256,  FALSE = 257,  GE    = 258,  ID   = 259,
      LE    = 260,  MINUS = 261,  NE   = 262, NUM   = 263,
      OR    = 264,  REAL  = 265,  TEMP  = 266,  TRUE = 267,
      BASIC = 268,                AND   = 269,    BREAK = 270,  
      DO   = 271, ELSE  = 272, IF    = 273,	INDEX = 274,   WHILE = 275,
      VOID  = 276,  CONST = 277,  FOR   = 278,  THEN = 279, SWITCH= 280,
      BEGIN = 281,  END   = 282,  NIL   = 283,  DIV  = 284, MOD   = 285,
   
   	  NOT = 286, CASE = 287, ARRAY = 288, OF = 289, DOWNTO = 290, FILE = 291,
   	  FUNCTION = 292, GOTO = 293, IN = 294, LABEL = 295, PACKED = 296, 
   	  PROCEDURE = 297, PROGRAM = 298, RECORD = 299, REPEAT = 300, SET = 301,
   	  TO = 302, TYPE = 303, UNTIL = 304, VAR = 305, WITH = 306;
   
   public static Map<Integer, String> symbols = new HashMap<Integer, String> ();
   
   static {
	   //终结符：关键字
	   symbols.put(Tag.PROGRAM, "p");
	   symbols.put(Tag.ID, "i");
	   symbols.put(Tag.BEGIN, "b");
	   symbols.put(Tag.END, "e");
	   symbols.put(Tag.IF, "f");
	   symbols.put(Tag.THEN, "t");
	   symbols.put(Tag.ELSE, "l");
	   symbols.put(Tag.WHILE, "w");
	   symbols.put(Tag.DO, "d");
	   symbols.put(Tag.NUM, "n");
	   symbols.put(Tag.UNTIL, "u");
	   symbols.put(Tag.REPEAT, "r");
	   //终结符：符号
	   symbols.put(Tag.EQ, "=");
	   symbols.put((int)'(', "(");
	   symbols.put((int)')', ")");
	   symbols.put((int)'+', "+");
	   symbols.put((int)'-', "-");
	   symbols.put((int)'*', "*");
	   symbols.put((int)'/', "/");
	   symbols.put((int)';', ";");
	   symbols.put((int)',', ",");
	   symbols.put((int)'.', ".");
	   symbols.put((int)'>', ">");
	   symbols.put((int)'<', "<");
	   symbols.put((int)'$', "$");
	   //非终结符
	   symbols.put((int)'S', "S");
	   symbols.put((int)'I', "I");
	   symbols.put((int)'C', "C");
	   symbols.put((int)'O', "O");
	   symbols.put((int)'A', "A");
	   symbols.put((int)'D', "D");
	   symbols.put((int)'B', "B");
	   symbols.put((int)'E', "E");
	   symbols.put((int)'T', "T");
	   symbols.put((int)'F', "F");
	   symbols.put((int)'N', "N");
   }
   
}
