package com.compilers.parser;

import com.compilers.lexer.Lexer;
import com.compilers.lexer.Tag;
import com.compilers.lexer.Token;
import com.compilers.threecode.SDT;
import com.compilers.tool.Stack;
import com.compilers.view.ResultTableModel;
import javafx.util.Pair;

/**
 * 语法分析器
 * @author CS
 */
public class Parser {

	//词法单元
	private Stack<Token> lexInput = new Stack<Token>();
	//符号栈
	private Stack<Token> lexStack = new Stack<Token>();;
	//状态栈
	private Stack<Integer> stateStack = new Stack<Integer>();;
	//步骤数
	private int step;
	//分析成功标志
	private boolean isSuccess;
	
	private StringBuffer lexInputBuff;
	private StringBuffer lexStackBuff;
	private StringBuffer stateStackBuff;
	private StringBuffer tacBuff;
	
	public Parser() {
		Tables.initTables();
		Production.initArr();
		this.initDatas();
	}
	
	/**
	 * 语法分析主过程
	 */
	public void analysis(Lexer lexer) {
		//词法分析器执行
		lexer.program();
		//返回词法单元序列
		lexInput = lexer.getLexList();
		
		//下一个输入字符串
		String peek;
		//命令：移入/规约
		String order;
		//命令数字
		int state;
		while (! lexInput.isEmpty()) {
			display();
			Token tmpToken = lexInput.pop();
			peek = Tag.symbols.get(tmpToken.tag);
			if (Tables.ACTION.get(new Pair<Integer, String>(stateStack.getFirst(), peek)) == null) {
				/**
				 * 出错
				 */
				ResultTableModel.initResult(step - 2, 4, "语法分析出错！");
				if (isSuccess) 
					isSuccess = false;
				continue;
			} else {
				order = Tables.ACTION.get(new Pair<Integer, String>(stateStack.getFirst(), peek));
			}
			if (order.equals("acc")) {
				/**
				 * 分析完毕
				 */
				String temp = "语法分析完毕！ ";
				if (isSuccess) {
					temp += "【成功】";
				} else {
					temp += "【失败】";
				}
				ResultTableModel.initResult(step - 2, 4, temp);
			} else {
				state = Integer.valueOf(order.substring(1, order.length()));
				if (order.charAt(0) == 'S') {
					/**
					 * 移入
					 */
					stateStack.push(state);
					lexStack.push(tmpToken);
					ResultTableModel.initResult(step - 2, 4, "转移至状态：" + state);
				} else if (order.charAt(0) == 'R') {
					/**
					 * 规约
					 */
					for (int i = 0; i < Production.ARR[state].getLength(); i ++) {
						stateStack.pop();
						//lexStack.pop();
					}
					int nextState = Tables.GOTO.get(new Pair<Integer, String>(
							stateStack.getFirst(), Production.ARR[state].getLeftValue()));
					stateStack.push(nextState);
					//lexStack.push(new Token(Production.ARR[state].getLeftValue().charAt(0)));
					lexStack = SDT.SDTRules(state, lexStack);
					lexInput.push(tmpToken);
					ResultTableModel.initResult(step - 2, 4, "按照  " + Production.ARR[state].getAllValue() + "  进行规约");
				} 
			}
		}
		tacBuff = SDT.outThreeAddrCode();
	}
	
	/**
	 * 初始化词法单元序列、状态栈、符号栈
	 */
	public void initDatas() {
		lexInput.clear();
		lexStack.clear();
		stateStack.clear();
		stateStack.push(0);

		lexInputBuff = new StringBuffer();
		lexStackBuff = new StringBuffer();
		stateStackBuff = new StringBuffer();
		
		isSuccess = true;
		step = 1;
	}
	
	void display() {
		lexInputBuff.delete(0, lexInputBuff.length());
		lexStackBuff.delete(0, lexStackBuff.length());
		stateStackBuff.delete(0, stateStackBuff.length());
		
		ResultTableModel.initResult(step - 1, 0, step);
		
		for(int i = stateStack.size() - 1; i >= 0; i --) {
			stateStackBuff.append(stateStack.get(i) + " ");
		}
		
		for(int i = lexStack.size() - 1; i >= 0; i --) {
			lexStackBuff.append(Tag.symbols.get(lexStack.get(i).tag));
		}
		
		for(int i = 0; i < lexInput.size(); i ++) {
			lexInputBuff.append(Tag.symbols.get(lexInput.get(i).tag));
		}
		
		ResultTableModel.initResult(step - 1, 1, stateStackBuff.toString());
		ResultTableModel.initResult(step - 1, 2, lexStackBuff.toString());
		ResultTableModel.initResult(step - 1, 3, lexInputBuff.toString());
		
		step ++;
	}
	
	public StringBuffer getTacBuff(){
		return tacBuff;
	}
	
}
