package com.compilers.threecode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import com.compilers.lexer.Token;
import com.compilers.tool.Stack;


public class SDT {
	
	public static int nextInstr = 100;
	public static int tempCount = 1;
	
	public static Token id, num, S, id_lists, compound_stmt, optional_stmts, stmts, stmts1,
					stmt, stmt1, stmt2, bool, expr, expr1, term, term1, factor, N;
	
	public static Map<Integer,String> threeAddressCode = new TreeMap<Integer,String>();

	public static Stack<Token> SDTRules(int state, Stack<Token> lexStack) {
		switch (state) {
		case 0:
			//S'→ S

			break;
		case 1:
			//S → program id ( id_lists );  compound_stmt .
			S = new Token('S');
			for (int i = 0; i < 8; i ++)
				lexStack.pop();
			
			lexStack.push(S);
			break;
		case 2:
			//id_lists → id  
			id_lists = new Token('I');
			lexStack.pop();
			
			lexStack.push(id_lists);
			break;
		case 3:
			//id_lists → id_lists , id 
			id_lists = new Token('I');
			lexStack.pop();
			lexStack.pop();
			lexStack.pop();
			
			lexStack.push(id_lists);
			break;
		case 4:
			//compound_stmt → begin optional_stmts end
			compound_stmt = new Token('C');
			lexStack.pop();
			optional_stmts = lexStack.pop();
			lexStack.pop();
			
			compound_stmt.first = optional_stmts.first;
			compound_stmt.nextList = optional_stmts.nextList;
			
			lexStack.push(compound_stmt);
			break;
		case 5:
			//optional_stmts → stmts
			optional_stmts = new Token('O');
			stmts = lexStack.pop();
			
			optional_stmts.first = stmts.first;
			optional_stmts.nextList = stmts.nextList;
			
			lexStack.push(optional_stmts);
			break;
		case 6:
			//optional_stmts → ε
			optional_stmts = new Token('O');
			
			
			lexStack.push(optional_stmts);
			break;
		case 7:
			//stmts → stmt
			stmts = new Token('A');
			stmt = lexStack.pop();
			
			stmts.first = stmt.first;
			stmts.nextList = stmt.nextList;
			
			lexStack.push(stmts);
			break;
		case 8:
			//stmts → stmts1; stmt 
			stmts = new Token('A');
			stmt = lexStack.pop();
			lexStack.pop();
			stmts1 = lexStack.pop();
			
			stmts.first = stmts1.first;
			stmts.nextList = stmt.nextList;
			
			lexStack.push(stmts);
			break;
		case 9:
			//stmt → id := expr
			stmt = new Token('D');
			expr = lexStack.pop();
			lexStack.pop();
			id = lexStack.pop();
			
			stmt.first = expr.first;
			stmt.nextList = expr.nextList;
			gen(id.lexeme + "=" + expr.addr);
			
			lexStack.push(stmt);
			break;
		case 10:
			//stmt → compound_stmt  
			stmt = new Token('D');
			compound_stmt = lexStack.pop();
			
			stmt.first = compound_stmt.first;
			stmt.nextList = compound_stmt.nextList;
			
			lexStack.push(stmt);
			break;
		case 11:
			//stmt → if bool then stmt1
			stmt = new Token('D');
			stmt1 = lexStack.pop();
			lexStack.pop();
			bool = lexStack.pop();
			lexStack.pop();
			
			backPatch(bool.trueList, stmt1.first); 
			stmt.nextList = merge(bool.falseList,stmt1.nextList);
			stmt.first = bool.first;
			
			lexStack.push(stmt);
			break;
		case 12:
			//stmt →  if bool then stmt1 N else stmt2  
			stmt = new Token('D');
			stmt2 = lexStack.pop();
			lexStack.pop();
			N = lexStack.pop();
			stmt1 = lexStack.pop();
			lexStack.pop();
			bool = lexStack.pop();
			lexStack.pop();

			backPatch(bool.trueList, stmt1.first);
			backPatch(bool.falseList, stmt2.first);
			backPatch(N.nextList, stmt2.nextList.get(0));
			List<Integer> tmp = merge(stmt1.nextList, N.nextList);
			stmt.nextList = merge(tmp,stmt2.nextList);
			stmt.first = bool.first;
			
			lexStack.push(stmt);
			break;
		case 13:
			//stmt → while bool  do stmt1
			stmt = new Token('D');
			stmt1 = lexStack.pop();
			lexStack.pop();
			bool = lexStack.pop();
			lexStack.pop();

			backPatch(stmt1.nextList, bool.first);
			backPatch(bool.trueList, stmt1.first);
			stmt.nextList = bool.falseList;
			gen("goto " + bool.first);
			stmt.first = bool.first;
			
			lexStack.push(stmt);
			break;
		case 14:
			//stmt → repeat stmts until bool
			stmt = new Token('D');
			bool = lexStack.pop();
			lexStack.pop();
			stmts = lexStack.pop();
			lexStack.pop();
			
			backPatch(bool.falseList, stmts.first);
			backPatch(bool.trueList, nextInstr);
			stmt.nextList = bool.trueList;
			stmt.first = stmts.first;
			
			lexStack.push(stmt);
			break;
		case 15:
			//bool → expr < expr1
			bool = new Token('B');
			expr1 = lexStack.pop();
			lexStack.pop();
			expr = lexStack.pop();
			
			bool.trueList = makeList(nextInstr);
			bool.falseList = makeList(nextInstr + 1);
			bool.first = expr.first;
			gen("if(" + expr.addr + "<" + expr1.addr + ") goto ");
			gen("goto ");
			
			lexStack.push(bool);
			break;
		case 16:
			//expr → expr1 + term
			expr = new Token('E');
			term = lexStack.pop();
			lexStack.pop();
			expr1 = lexStack.pop();
			
			expr.addr = newTemp();
			expr.first = expr1.first;
			expr.nextList = makeList(nextInstr + 1);
			gen(expr.addr + "=" + expr1.addr + "+" + term.addr);
			
			lexStack.push(expr);
			break;
		case 17:
			//expr → expr1 - term
			expr = new Token('E');
			term = lexStack.pop();
			lexStack.pop();
			expr1 = lexStack.pop();

			expr.addr = newTemp();
			expr.first = expr1.first;
			expr.nextList = makeList(nextInstr + 1);
			gen(expr.addr + "=" + expr1.addr + "-" + term.addr);
			
			lexStack.push(expr);	
			break;
		case 18:
			//expr → term
			expr = new Token('E');
			term = lexStack.pop();
			
			expr.addr = term.addr;
			expr.first = term.first;
			expr.nextList = makeList(nextInstr + 1);
			
			lexStack.push(expr);
			break;
		case 19:
			//term → term1 * factor
			term = new Token('T');
			factor = lexStack.pop();
			lexStack.pop();
			term1 = lexStack.pop();
			
			term.addr = newTemp();
			term.first = term1.first;
			gen(term.addr + "=" + term1.addr + "*" + factor.addr);
			
			lexStack.push(term);
			break;
		case 20:
			//term → term1 / factor
			term = new Token('T');
			factor = lexStack.pop();
			lexStack.pop();
			term1 = lexStack.pop();
			
			term.addr = newTemp();
			term.first = term1.first;
			gen(term.addr + "=" + term1.addr + "/" + factor.addr);
			
			lexStack.push(term);
			break;
		case 21:
			//term → factor
			term = new Token('T');
			factor = lexStack.pop();
			
			term.addr = factor.addr;
			term.first = nextInstr;
			
			lexStack.push(term);
			break;
		case 22:
			//factor → id
			factor = new Token('F');
			id = lexStack.pop();
			
			factor.addr = id.lexeme;
			
			lexStack.push(factor);
			break;
		case 23:
			//factor → num
			factor = new Token('F');
			num = lexStack.pop();
			
			factor.addr = num.lexeme;
			
			lexStack.push(factor);
			break;
		case 24:
			//N → ε
			N = new Token('N');
			N.nextList = makeList(nextInstr);
			
			gen("goto ");
			
			lexStack.push(N);
			break;
		}
		
		return lexStack;
	}
	
	/**
	 * 创建列表
	 * @param label
	 * @return
	 */
	private static List<Integer> makeList(int label) {
		List<Integer> temp = new ArrayList<Integer>();
		temp.add(label);
		return temp;
	}
	
	/**
	 * 合并列表
	 * @param l1
	 * @param l2
	 * @return
	 */
	private static List<Integer> merge(List<Integer> l1,List<Integer> l2) {
		if(l1 == null)
			return l2;
		if(l2 == null)
			return l1;
		
		l1.addAll(l2);
		return l1;
	}
	
	/**
	 * 添加三地址码
	 * @param s
	 */
	private static void gen(String s) {
		threeAddressCode.put(nextInstr ++, s);
	}
	
	/**
	 * 回填一条指令
	 * 将destLabel回填到label所指向的三个地址码的尾部
	 * @param label
	 * @param destLabel
	 */
	private static void backPatch(int label, int destLabel) {
		if(label == -1)
			return ;
		String code = threeAddressCode.get(label);
		if(code == null)
		{
			System.out.println("回填错误，找不到指定标号的三地址码!");
			return ;
		}
		
		code += Integer.toString(destLabel);
		threeAddressCode.put(label, code);
	}
	
	/**
	 * 回填多条指令
	 * @param label
	 * @param destLabel
	 */
	private static void backPatch(List<Integer> label, int destLabel) {
		if(label == null)
			return ;
		Iterator<Integer> iter = label.iterator();
		while(iter.hasNext()) {
			backPatch(iter.next(), destLabel);
		}
	
	}
	
	/**
	 * 输出三地址码
	 */
	public static StringBuffer outThreeAddrCode() {
		StringBuffer tacBuffer = new StringBuffer();
		String reString="";
		for(int i = 100;i < nextInstr; i ++)
		{
			reString+=(i + ":");
			reString += (threeAddressCode.get(i) + "\n");
		}
		tacBuffer.append(reString);
		tacBuffer.append(nextInstr + ":");
		return tacBuffer;
	}
	
	public static String newTemp() {
		return "t" + (tempCount ++ + "");
	}
	
	/**
	 * 重置数据
	 */
	public static void resetData() {
		nextInstr = 100;
		tempCount = 1;
		threeAddressCode.clear();
	}
	
}
