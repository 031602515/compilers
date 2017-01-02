package com.compilers.lexer;

public class Num extends Token {
	
	public Num(long v) { super(v + "", Tag.NUM);}
	
	public Num(String radixNum, char radixType) {
		super(Tag.NUM);
		lexeme = transRadix(radixNum, radixType) + "";
	}
	
	public String toString() { return "<num," + lexeme + ">" + "\n"; }
	public void printData() {
		System.out.println("<num," + lexeme + ">");
	}
	
	//把十六进制数转换为十进制数
	long transRadix(String numStr, char radixType) {
		if( radixType == 'X' || radixType == 'x' ) {
			radixType = 16;
		} else if (radixType == 'Y' || radixType == 'y') {
			radixType = 24;
		}
		char[] str = numStr.toCharArray();
		int temp;
		long num = 0;
		for (int i = str.length - 1; i >= 0; i --) {
			if (str[i] >= 65 && str[i] <= 90) {
				temp = str[i] - 55;
			} else if (str[i] >= 97 && str[i] <= 122) {
				temp = str[i] - 87;
			} else {
				temp = str[i] - 48;
			}
			num += temp * Math.pow(radixType, str.length - 1 - i);
		}
		return num;
	}
		
}
