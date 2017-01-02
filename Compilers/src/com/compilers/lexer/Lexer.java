package com.compilers.lexer;

import java.util.Hashtable;

import com.compilers.tool.Stack;

public class Lexer {
	
   public int line = 0;
   private char peek = ' ';
   private Token look;
   private StringBuffer temp = new StringBuffer();
   private String[] dataSep;
   private Stack<Token> lexList = new Stack<Token>();
   private int index, idIndex;
   
   Hashtable<String, Word> words;
   
   StringBuffer output = new StringBuffer();
   StringBuffer idDatas = new StringBuffer();
   StringBuffer symbol = new StringBuffer();
   
   String input;
   String keyword = "	EQ,  FALSE,  GE,  ID,  PROCEDURE\n"
			+ "	MINUS,  NE,  NUM,  RECORD,  REAL\n"
			+ "	TEMP,  TRUE,  BASIC,  AND,  WITH\n"
			+ "	DO,  ELSE,  THEN,  INDEX,  WHILE\n"	
			+ "	VOID,  CONST,  FOR,  IF,  SWITCH\n"
			+ "	BEGIN,  END,  NIL,  PACKED,  MOD\n"
			+ "	NOT,  CASE,  ARRAY,  OF,  DOWNTO\n"
			+ "	FILE,  FUNCTION,  GOTO,  IN,  TO\n"
			+ "	DIV,  LE,  PROGRAM,  OR,  REPEAT\n"
			+ "	SET,  LABEL,  TYPE,  UNTIL,  VAR\n"
			+ "	BREAK\n";
      
   void reserve(Word w) { words.put(w.lexeme, w); }

   public Lexer() {
	  initWords();
      output.append("一、词法单元如下：\n");
	  symbol.append("\n二、符号表如下：\n1、关键字\n");
	  symbol.append(keyword);
	  idDatas.append("2、标识符\n");
   }
   
   void initWords() {
	   words = new Hashtable<String, Word>();
	   
 	   reserve( new Word("IF",    Tag.IF)    );
	   reserve( new Word("if",    Tag.IF)    );
	      
	   reserve( new Word("ELSE",  Tag.ELSE)  );
	   reserve( new Word("else",  Tag.ELSE)  );

	   reserve( new Word("WHILE", Tag.WHILE) );
	   reserve( new Word("while", Tag.WHILE) );
	
	  reserve( new Word("DO",    Tag.DO)    );
	  reserve( new Word("do",    Tag.DO)    );
	
	  reserve( new Word("BREAK", Tag.BREAK) );
	  reserve( new Word("break", Tag.BREAK) );
	  
	  reserve( new Word("VOID",  Tag.VOID)  );
	  reserve( new Word("void",  Tag.VOID)  );
	
	  reserve( new Word("CONST", Tag.CONST) );
	  reserve( new Word("const", Tag.CONST) );

	  reserve( new Word("FOR",   Tag.FOR)   );
	  reserve( new Word("for",   Tag.FOR)   );
	
	  reserve( new Word("THEN",  Tag.THEN)  );
	  reserve( new Word("then",  Tag.THEN)  );
	
	  reserve( new Word("SWITCH",Tag.SWITCH));
	  reserve( new Word("switch",Tag.SWITCH));
	
	  reserve( new Word("BEGIN", Tag.BEGIN) );
	  reserve( new Word("begin", Tag.BEGIN) );
	
	  reserve( new Word("END",   Tag.END)   );
	  reserve( new Word("end",   Tag.END)   );
	
	  reserve( new Word("NIL",   Tag.NIL)   );
	  reserve( new Word("nil",   Tag.NIL)   );
	
	  reserve( new Word("DIV",   Tag.DIV)   );
	  reserve( new Word("div",   Tag.DIV)   );
	
	  reserve( new Word("MOD",   Tag.MOD)   );
	  reserve( new Word("mod",   Tag.MOD)   );
	  
	  reserve( new Word("NOT",   Tag.NOT)   );
	  reserve( new Word("not",   Tag.NOT)   );
	  
	  reserve( new Word("CASE",   Tag.CASE)   );
	  reserve( new Word("case",   Tag.CASE)   );
	  
	  reserve( new Word("ARRAY",   Tag.ARRAY)   );
	  reserve( new Word("array",   Tag.ARRAY)   );
	  
	  reserve( new Word("OF",   Tag.OF)   );
	  reserve( new Word("of",   Tag.OF)   );
	  
	  reserve( new Word("DOWNTO",   Tag.DOWNTO)   );
	  reserve( new Word("downto",   Tag.DOWNTO)   );
	  
	  reserve( new Word("FILE",   Tag.FILE)   );
	  reserve( new Word("file",   Tag.FILE)   );
	  
	  reserve( new Word("FUNCTION",   Tag.FUNCTION)   );
	  reserve( new Word("funtion",   Tag.FUNCTION)   );
	  
	  reserve( new Word("GOTO",   Tag.GOTO)   );
	  reserve( new Word("goto",   Tag.GOTO)   );
	  
	  reserve( new Word("IN",   Tag.IN)   );
	  reserve( new Word("in",   Tag.IN)   );
	  
	  reserve( new Word("LABEL",   Tag.LABEL)   );
	  reserve( new Word("label",   Tag.LABEL)   );
	  
	  reserve( new Word("PACKED",   Tag.PACKED)   );
	  reserve( new Word("packed",   Tag.PACKED)   );
	  
	  reserve( new Word("PROCEDURE",   Tag.PROCEDURE)   );
	  reserve( new Word("procedure",   Tag.PROCEDURE)   );
	  
	  reserve( new Word("PROGRAM",   Tag.PROGRAM)   );
	  reserve( new Word("program",   Tag.PROGRAM)   );
	  
	  reserve( new Word("RECORD",   Tag.RECORD)   );
	  reserve( new Word("record",   Tag.RECORD)   );
	  
	  reserve( new Word("REPEAT",   Tag.REPEAT)   );
	  reserve( new Word("repeat",   Tag.REPEAT)   );
	  
	  reserve( new Word("SET",   Tag.SET)   );
	  reserve( new Word("set",   Tag.SET)   );
	  
	  reserve( new Word("TO",   Tag.TO)   );
	  reserve( new Word("to",   Tag.TO)   );
	  
	  reserve( new Word("TYPE",   Tag.TYPE)   );
	  reserve( new Word("type",   Tag.TYPE)   );
	  
	  reserve( new Word("UNTIL",   Tag.UNTIL)   );
	  reserve( new Word("until",   Tag.UNTIL)   );
	
	  reserve( new Word("VAR",   Tag.VAR)   );
	  reserve( new Word("var",   Tag.VAR)   );
	
	  reserve( new Word("WITH",   Tag.WITH)   );
	  reserve( new Word("with",   Tag.WITH)   );
	  
	  reserve( Word.True );  reserve( Word.False );
   }

   public void program() {
	   for (int i = 0; i < dataSep.length; i ++) {
		   line ++;
		   for (index = 0; index < dataSep[i].length(); ) {
				look = this.scan(dataSep[i]);
			   if (look == null) {
				   break;
			   } else {
				   lexList.push(look);
			   }
		   }
	   }
	   lexList.push(new Token('$'));
	   lexList = lexList.inverse();
   }
   
   void readch() {
	   if (index + 1 > input.length()) {
		   peek = '\r';
	   } else {
		   peek = input.charAt(index ++);
	   }
   }
   
   boolean readch(char c) {
      readch();
      if( peek != c ) return false;
      peek = ' ';
      return true;
   }
   
   public Token scan(String input) {
	  this.input = input;
	  Token tok;
	  
	  //换行符处理
	  if (peek == '\n' || peek == '\r')
		  peek = ' ';
      for(; ; readch() ) {
         if( peek == ' ' || peek == '\t' ) continue;
         else if( peek == '\n' || peek == '\r' ) return null;
         else break;
      }
      /**
       * 向前看符号判断
       * 双符号运算符处理
       */
      switch( peek ) {
      case '/':
    	  /**
    	   * 注释
    	   */
          if( readch('/') ) { output.append("Line_" + line + ": 单行注释，注释位置为第" + line + "行\n"); return null; } 
          else { peek = '/'; index --; break; }
      case '&':
          if( readch('&') ) { output.append(Word.and.toString()); return Word.and; } 
          else { peek = '&'; index --; break; }
       case '|':
          if( readch('|') ) { output.append(Word.or.toString()); return Word.or; } 
          else { peek = '|'; index --; break; }
       case '=':
          if( readch('=') ) { output.append(Word.eq.toString()); return Word.eq; }
          else { peek = '='; index --; break; }
       case '<':
           if( readch('=') ) { output.append(Word.le.toString()); return Word.le; } 
           else if (peek == '>') {  peek = ' '; output.append(Word.ne.toString()); return Word.ne; }
           else { peek = '<'; index --; break; }
       case '>':
           if( readch('=') ) { output.append(Word.ge.toString()); return Word.ge; } 
           else { peek = '>'; index --; break; }
       case ':':
           if( readch('=') ) { output.append(Word.eq.toString()); return Word.eq; } 
           else { peek = ':'; index --; break; }
      case '0':
    	  /**
    	   * 十六进制以及二十四进制数转换
    	   */
    	  readch();
    	  temp.delete(0, temp.length());
    	  if( peek == 'X' || peek == 'x' || peek == 'Y' || peek == 'y') {
    		  char radixType = peek;
    		  for (readch();; readch()) {
    			  if ( peek == ' ' || peek == ';' || peek == '\r')
    				  break;
    			  temp.append(peek);
    		  }
    		  Num num = new Num(temp.toString(), radixType);
    		  output.append(num.toString());
    		  return num;
       	  } else if ( peek == '.') {
    		  float x = 0; float d = 10;
    		  for(;;) {
                 readch();
                 if( ! Character.isDigit(peek) ) break;
                 x = x + Character.digit(peek, 10) / d; d = d * 10;
    		  }
    		  Real real = new Real(x);
     		  output.append(real.toString());
     		  return real;
    	  } else {
         	 Num num = new Num(0);
     		 output.append(num.toString());
         	 return num;
    	  }
      }
      /**
       * 数字词法单元
       */
      if( Character.isDigit(peek) ) {
         long v = 0;
         do {
            v = 10*v + Character.digit(peek, 10); readch();
         } while( Character.isDigit(peek) );
    	 if (v >= Math.pow(2, 31)) {
    		 output.append("Line_" + line + ": 整数越界错误" + "\n");
    		 return null;
    	 } else {
             if( peek != '.' ) {
            	 Num num = new Num(v);
        		 output.append(num.toString());
            	 return num;
        	 }
             float x = v; float d = 10;
             for(;;) {
                readch();
                if( ! Character.isDigit(peek) ) break;
                x = x + Character.digit(peek, 10) / d; d = d*10;
             }
             Real real = new Real(x);
    		 output.append(real.toString());
             return real;
         }
      }
      /**
       * 字符串单元
       */
      if( Character.isLetter(peek) ) {
         StringBuffer b = new StringBuffer();
         do {
            b.append(peek); 
            readch();
            if (peek == '\n' || peek == '\r') break;
         } while( Character.isLetterOrDigit(peek) );
         if (peek != '\n' && peek != '\r') {
             peek = ' ';
             index --; 
         }
         String s = b.toString();
         Word w = (Word)words.get(s);
         if( w != null ) {
    		 output.append(w.toString());
        	 return w;
         }
         w = new Word(s, Tag.ID);
         idDatas.append("	(" + (idIndex ++) + "):   " + w.lexeme + "\n");
         words.put(s, w);
		 output.append(w.toString());
         return w;
      }
      tok = new Token(peek); 
      peek = ' ';
      output.append(tok.toString());
      return tok;
   }
	
	public void resetData() {
		idIndex = 1;
		line = 0;
		output.delete(0, output.length());
	    output.append("一、词法单元如下：\n");
	    symbol.delete(0, symbol.length());
	    symbol.append("\n二、符号表如下：\n1、关键字\n");
	    symbol.append(keyword);
	    idDatas.delete(0, idDatas.length());
	    idDatas.append("2、标识符\n");
		peek = ' ';
		initWords();
	}

	public StringBuffer getSymbol() {
		return symbol;
	}

	public StringBuffer getIdDatas() {
		return idDatas;
	}

	public StringBuffer getOutput() {
		return output;
	}

	public void setOutput(StringBuffer output) {
		this.output = output;
	}
	
	public Stack<Token> getLexList() {
		return lexList;
	}
	
	public void setDataSep(String[] dataSep) {
		this.dataSep = dataSep;
	}
	
}
