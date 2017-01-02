package com.compilers.view;

import javax.swing.table.AbstractTableModel;

public class ResultTableModel extends AbstractTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static Object[][] result;
	public static int length;
	
	String[] titles = {"步骤", "状态栈", "符号栈", "输入", "动作"};
	
	public static void resetObject() {
		for (int i = 0; i < length; i ++) {
			for (int j = 0; j < 5; j ++) {
				result[i][j] = "";
			}
		}
	}
	
	public static void initResultLength(int length){
		ResultTableModel.length = length;
		result = new Object[length][5];
	}
	
	public static void initResult(int row, int col, String stack){
		
		result[row][col] = stack.toString();
	}
	
	public static void initResult(int row, int col, int step){
		
		result[row][col] = step;
	}
	
	public int getColumnCount()	{
	
		return titles.length;        
	}

	public int getRowCount(){
		
		return result.length;   
	}

	public String getColumnName(int col){

		
		return titles[col];
	}

	public Object getValueAt(int row, int col){
		        
		return result[row][col];
	}

	/*public Class getColumnClass(int c) {
			    
		return getValueAt(0, c).getClass();
	}*/
}

