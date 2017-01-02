package com.compilers.parser;

/**
* 				文法符号缩减
*   -----------------------------------------
	| 	       终结符		|		非终结符			|
	|	program	p	|	S				S	|
	|	id		i	|	id_lists		I	|
	|	begin	b	|	compound_stmt	C	|
	|	end		e	|	optional_stmts	O	|
	|	if		f	|	stmts			A	|
	|	then	t	|	stmt			D	|
	|	else	l	|	bool			B	|
	|	while	w	|	expr			E	|
	|	do		d	|	term			T	|
	|	num		n	|	factor			F	|
	|	repeat	r	|						|
	|	until	u	|						|
	－－－－－－－－－－－－－－－－－－－－－－－－－－－
*/

/**
 * 产生式
 * @author CS
 *
 */
public class Production {

	//产生式左值
	private String leftValue;
	//产生式右值
	private String rightValue;
	//产生式全值
	private String allValue;
	//产生式右值长度
	private int length;
	//文法的所有产生式
	public static Production[] ARR = new Production[50];
	
	Production() {}
	
	Production(String lv, String rv) {
		this.leftValue = lv;
		this.rightValue = rv;
		allValue = leftValue + " -> " + rightValue;
		if (rightValue.equals("ε")) {
			this.length = 0;
		} else {
			this.length = rv.length();
		}
	}

	/**
	 * 初始化产生式
	 */
	public static void initArr() {
		ARR[0]  = new Production("H", "S");
		ARR[1]  = new Production("S", "pi(I);C.");
		ARR[2]  = new Production("I", "i");
		ARR[3]  = new Production("I", "I,i");
		ARR[4]  = new Production("C", "bOe");
		ARR[5]  = new Production("O", "A");
		ARR[6]  = new Production("O", "ε");
		ARR[7]  = new Production("A", "D");
		ARR[8]  = new Production("A", "A;D");
		ARR[9]  = new Production("D", "i=E");
		ARR[10] = new Production("D", "C");
		ARR[11] = new Production("D", "fBtD");
		ARR[12] = new Production("D", "fBtDNlD");
		ARR[13] = new Production("D", "wBdD");
		ARR[14] = new Production("D", "rAuB");
		ARR[15] = new Production("B", "E<E");
		ARR[16] = new Production("E", "E+T");
		ARR[17] = new Production("E", "E-T");
		ARR[18] = new Production("E", "T");
		ARR[19] = new Production("T", "T*F");
		ARR[20] = new Production("T", "T/F");
		ARR[21] = new Production("T", "F");
		ARR[22] = new Production("F", "i");
		ARR[23] = new Production("F", "n");
		ARR[24] = new Production("N", "ε");
	}
	
	public String getLeftValue() {
		return leftValue;
	}

	public String getRightValue() {
		return rightValue;
	}

	public String getAllValue() {
		return allValue;
	}

	public int getLength() {
		return length;
	}
	
}
