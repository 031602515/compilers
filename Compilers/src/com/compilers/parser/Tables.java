package com.compilers.parser;
import java.util.HashMap;
import java.util.Map;
import javafx.util.Pair;

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
 * SLR(1)分析表
 * @author CS
 *
 */
public class Tables {
	
	//ACTION表
	public static Map<Pair<Integer, String>, String> ACTION = new HashMap<Pair<Integer, String>, String>();
	//GOTO表
	public static Map<Pair<Integer, String>, Integer> GOTO = new HashMap<Pair<Integer, String>, Integer>();

	/**
	 * 初始化SLR(1)分析表
	 */
	public static void initTables() {
		//初始化ACTION表
		ACTION.put(new Pair<Integer, String>(0, "p"), "S2");
		
		ACTION.put(new Pair<Integer, String>(1, "$"), "acc");
		
		ACTION.put(new Pair<Integer, String>(2, "i"), "S3");
		
		ACTION.put(new Pair<Integer, String>(3, "("), "S4");
		
		ACTION.put(new Pair<Integer, String>(4, "i"), "S6");
		
		ACTION.put(new Pair<Integer, String>(5, ")"), "S7");
		ACTION.put(new Pair<Integer, String>(5, ","), "S8");
		
		ACTION.put(new Pair<Integer, String>(6, ")"), "R2");
		ACTION.put(new Pair<Integer, String>(6, ","), "R2");
		
		ACTION.put(new Pair<Integer, String>(7, ";"), "S9");
		
		ACTION.put(new Pair<Integer, String>(8, "i"), "S10");
		
		ACTION.put(new Pair<Integer, String>(9, "b"), "S12");
		
		ACTION.put(new Pair<Integer, String>(10, ")"), "R3");
		ACTION.put(new Pair<Integer, String>(10, ","), "R3");
		
		ACTION.put(new Pair<Integer, String>(11, "."), "S13");
		
		ACTION.put(new Pair<Integer, String>(12, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(12, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(12, "e"), "R6");
		ACTION.put(new Pair<Integer, String>(12, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(12, "r"), "S21");
		ACTION.put(new Pair<Integer, String>(12, "w"), "S20");
		
		ACTION.put(new Pair<Integer, String>(13, "$"), "R1");
		
		ACTION.put(new Pair<Integer, String>(14, "e"), "S22");
		
		ACTION.put(new Pair<Integer, String>(15, ";"), "S23");
		ACTION.put(new Pair<Integer, String>(15, "e"), "R5");
		
		ACTION.put(new Pair<Integer, String>(16, ";"), "R7");
		ACTION.put(new Pair<Integer, String>(16, "e"), "R7");
		ACTION.put(new Pair<Integer, String>(16, "u"), "R7");
		
		ACTION.put(new Pair<Integer, String>(17, "="), "S24");
		
		ACTION.put(new Pair<Integer, String>(18, ";"), "R10");
		ACTION.put(new Pair<Integer, String>(18, "e"), "R10");
		ACTION.put(new Pair<Integer, String>(18, "l"), "R10");
		
		ACTION.put(new Pair<Integer, String>(19, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(19, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(20, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(20, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(21, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(21, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(21, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(21, "w"), "S20");
		ACTION.put(new Pair<Integer, String>(21, "r"), "S21");
		
		ACTION.put(new Pair<Integer, String>(22, ";"), "R4");
		ACTION.put(new Pair<Integer, String>(22, "."), "R4");
		ACTION.put(new Pair<Integer, String>(22, "e"), "R4");
		ACTION.put(new Pair<Integer, String>(22, "l"), "R4");
		ACTION.put(new Pair<Integer, String>(22, "u"), "R4");
		
		ACTION.put(new Pair<Integer, String>(23, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(23, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(23, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(23, "w"), "S20");
		ACTION.put(new Pair<Integer, String>(23, "r"), "S21");
		
		ACTION.put(new Pair<Integer, String>(24, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(24, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(25, "t"), "S35");
		
		ACTION.put(new Pair<Integer, String>(26, "<"), "S36");
		ACTION.put(new Pair<Integer, String>(26, "+"), "S37");
		ACTION.put(new Pair<Integer, String>(26, "-"), "S38");
		
		ACTION.put(new Pair<Integer, String>(27, ";"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "e"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "t"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "l"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "d"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "<"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "+"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "-"), "R18");
		ACTION.put(new Pair<Integer, String>(27, "*"), "S39");
		ACTION.put(new Pair<Integer, String>(27, "/"), "S40");
		ACTION.put(new Pair<Integer, String>(27, "u"), "R18");
		
		ACTION.put(new Pair<Integer, String>(28, ";"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "e"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "t"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "l"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "d"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "<"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "+"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "-"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "*"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "/"), "R21");
		ACTION.put(new Pair<Integer, String>(28, "u"), "R21");
		
		ACTION.put(new Pair<Integer, String>(29, ";"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "e"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "t"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "l"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "d"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "<"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "+"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "-"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "*"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "/"), "R22");
		ACTION.put(new Pair<Integer, String>(29, "u"), "R22");
		
		ACTION.put(new Pair<Integer, String>(30, ";"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "e"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "t"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "l"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "d"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "<"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "+"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "-"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "*"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "/"), "R23");
		ACTION.put(new Pair<Integer, String>(30, "u"), "R23");
		
		ACTION.put(new Pair<Integer, String>(31, "d"), "S41");
		
		ACTION.put(new Pair<Integer, String>(32, ";"), "S23");
		ACTION.put(new Pair<Integer, String>(32, "u"), "S42");
		
		ACTION.put(new Pair<Integer, String>(33, ";"), "R8");
		ACTION.put(new Pair<Integer, String>(33, "e"), "R8");
		ACTION.put(new Pair<Integer, String>(33, "u"), "R8");
		
		ACTION.put(new Pair<Integer, String>(34, ";"), "R9");
		ACTION.put(new Pair<Integer, String>(34, "e"), "R9");
		ACTION.put(new Pair<Integer, String>(34, "l"), "R9");
		ACTION.put(new Pair<Integer, String>(34, "+"), "S37");
		ACTION.put(new Pair<Integer, String>(34, "-"), "S38");
		ACTION.put(new Pair<Integer, String>(34, "u"), "R9");
		
		ACTION.put(new Pair<Integer, String>(35, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(35, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(35, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(35, "w"), "S20");
		ACTION.put(new Pair<Integer, String>(35, "r"), "S21");
		
		ACTION.put(new Pair<Integer, String>(36, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(36, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(37, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(37, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(38, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(38, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(39, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(39, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(40, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(40, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(41, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(41, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(41, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(41, "w"), "S20");
		ACTION.put(new Pair<Integer, String>(41, "r"), "S21");

		ACTION.put(new Pair<Integer, String>(42, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(42, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(43, ";"), "R11");
		ACTION.put(new Pair<Integer, String>(43, "e"), "R11");
		ACTION.put(new Pair<Integer, String>(43, "l"), "R24");
		ACTION.put(new Pair<Integer, String>(43, "N"), "S51");
		
		ACTION.put(new Pair<Integer, String>(44, "t"), "R15");
		ACTION.put(new Pair<Integer, String>(44, "d"), "R15");
		ACTION.put(new Pair<Integer, String>(44, ";"), "R15");
		ACTION.put(new Pair<Integer, String>(44, "e"), "R15");
		ACTION.put(new Pair<Integer, String>(44, "l"), "R15");
		ACTION.put(new Pair<Integer, String>(44, "u"), "R15");
		ACTION.put(new Pair<Integer, String>(44, "+"), "S52");
		ACTION.put(new Pair<Integer, String>(44, "-"), "S53");
		
		ACTION.put(new Pair<Integer, String>(45, ";"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "e"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "t"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "l"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "d"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "<"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "+"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "-"), "R16");
		ACTION.put(new Pair<Integer, String>(45, "*"), "S39");
		ACTION.put(new Pair<Integer, String>(45, "/"), "S40");
		ACTION.put(new Pair<Integer, String>(45, "u"), "R16");
		
		ACTION.put(new Pair<Integer, String>(46, ";"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "e"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "t"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "l"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "d"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "<"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "+"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "-"), "R17");
		ACTION.put(new Pair<Integer, String>(46, "*"), "S39");
		ACTION.put(new Pair<Integer, String>(46, "/"), "S40");
		ACTION.put(new Pair<Integer, String>(46, "u"), "R17");
		
		ACTION.put(new Pair<Integer, String>(47, ";"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "e"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "t"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "l"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "d"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "<"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "+"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "-"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "*"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "/"), "R19");
		ACTION.put(new Pair<Integer, String>(47, "u"), "R19");
		
		ACTION.put(new Pair<Integer, String>(48, ";"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "e"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "t"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "l"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "d"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "<"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "+"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "-"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "*"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "/"), "R20");
		ACTION.put(new Pair<Integer, String>(48, "u"), "R20");
		
		ACTION.put(new Pair<Integer, String>(49, ";"), "R13");
		ACTION.put(new Pair<Integer, String>(49, "e"), "R13");
		ACTION.put(new Pair<Integer, String>(49, "l"), "R13");
		
		ACTION.put(new Pair<Integer, String>(50, ";"), "R14");
		ACTION.put(new Pair<Integer, String>(50, "e"), "R14");
		ACTION.put(new Pair<Integer, String>(50, "l"), "R14");
		
		ACTION.put(new Pair<Integer, String>(51, "i"), "S17");
		ACTION.put(new Pair<Integer, String>(51, "b"), "S12");
		ACTION.put(new Pair<Integer, String>(51, "f"), "S19");
		ACTION.put(new Pair<Integer, String>(51, "w"), "S20");
		ACTION.put(new Pair<Integer, String>(51, "r"), "S21");
		
		ACTION.put(new Pair<Integer, String>(52, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(52, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(53, "i"), "S29");
		ACTION.put(new Pair<Integer, String>(53, "n"), "S30");
		
		ACTION.put(new Pair<Integer, String>(54, ";"), "R12");
		ACTION.put(new Pair<Integer, String>(54, "e"), "R12");
		ACTION.put(new Pair<Integer, String>(54, "l"), "R12");
		ACTION.put(new Pair<Integer, String>(54, "u"), "R12");
		
		ACTION.put(new Pair<Integer, String>(55, ";"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "e"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "t"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "l"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "d"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "<"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "+"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "-"), "R16");
		ACTION.put(new Pair<Integer, String>(55, "*"), "S39");
		ACTION.put(new Pair<Integer, String>(55, "/"), "S40");
		ACTION.put(new Pair<Integer, String>(55, "u"), "R16");
		
		ACTION.put(new Pair<Integer, String>(56, ";"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "e"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "t"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "l"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "d"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "<"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "+"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "-"), "R17");
		ACTION.put(new Pair<Integer, String>(56, "*"), "S39");
		ACTION.put(new Pair<Integer, String>(56, "/"), "S40");
		ACTION.put(new Pair<Integer, String>(56, "u"), "R17");
		
		ACTION.put(new Pair<Integer, String>(57, "l"), "S51");
		
		//初始化GOTO表
		GOTO.put(new Pair<Integer, String>(0, "S"), 1);
		
		GOTO.put(new Pair<Integer, String>(4, "I"), 5);
		
		GOTO.put(new Pair<Integer, String>(9, "C"), 11);
		
		GOTO.put(new Pair<Integer, String>(12, "C"), 18);
		GOTO.put(new Pair<Integer, String>(12, "O"), 14);
		GOTO.put(new Pair<Integer, String>(12, "A"), 15);
		GOTO.put(new Pair<Integer, String>(12, "D"), 16);
		
		GOTO.put(new Pair<Integer, String>(19, "B"), 25);
		GOTO.put(new Pair<Integer, String>(19, "E"), 26);
		GOTO.put(new Pair<Integer, String>(19, "T"), 27);
		GOTO.put(new Pair<Integer, String>(19, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(20, "B"), 31);
		GOTO.put(new Pair<Integer, String>(20, "E"), 26);
		GOTO.put(new Pair<Integer, String>(20, "T"), 27);
		GOTO.put(new Pair<Integer, String>(20, "F"), 28);

		GOTO.put(new Pair<Integer, String>(21, "C"), 18);
		GOTO.put(new Pair<Integer, String>(21, "A"), 32);
		GOTO.put(new Pair<Integer, String>(21, "D"), 16);
		
		GOTO.put(new Pair<Integer, String>(23, "C"), 18);
		GOTO.put(new Pair<Integer, String>(23, "D"), 33);
		
		GOTO.put(new Pair<Integer, String>(24, "E"), 34);
		GOTO.put(new Pair<Integer, String>(24, "T"), 27);
		GOTO.put(new Pair<Integer, String>(24, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(35, "C"), 18);
		GOTO.put(new Pair<Integer, String>(35, "D"), 43);
		
		GOTO.put(new Pair<Integer, String>(36, "E"), 44);
		GOTO.put(new Pair<Integer, String>(36, "T"), 27);
		GOTO.put(new Pair<Integer, String>(36, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(37, "T"), 45);
		GOTO.put(new Pair<Integer, String>(37, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(38, "T"), 46);
		GOTO.put(new Pair<Integer, String>(38, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(39, "F"), 47);
		
		GOTO.put(new Pair<Integer, String>(40, "F"), 48);
		
		GOTO.put(new Pair<Integer, String>(41, "C"), 18);
		GOTO.put(new Pair<Integer, String>(41, "D"), 49);
		
		GOTO.put(new Pair<Integer, String>(41, "N"), 43);
		
		GOTO.put(new Pair<Integer, String>(42, "B"), 50);
		GOTO.put(new Pair<Integer, String>(42, "E"), 26);
		GOTO.put(new Pair<Integer, String>(42, "T"), 27);
		GOTO.put(new Pair<Integer, String>(42, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(43, "N"), 57);
		
		GOTO.put(new Pair<Integer, String>(51, "C"), 18);
		GOTO.put(new Pair<Integer, String>(51, "D"), 54);
		
		GOTO.put(new Pair<Integer, String>(52, "T"), 55);
		GOTO.put(new Pair<Integer, String>(52, "F"), 28);
		
		GOTO.put(new Pair<Integer, String>(53, "T"), 56);
		GOTO.put(new Pair<Integer, String>(53, "F"), 28);
	}
	
}
