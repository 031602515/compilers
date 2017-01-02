package com.compilers.view;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

import com.compilers.lexer.Lexer;
import com.compilers.parser.Parser;
import com.compilers.threecode.SDT;

public class AnalyzerView extends JFrame implements ActionListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final int WIDTH = 1024;
	static final int HEIGTH = 705;
	
	private File file = null;
	//词法分析文本框
	JTextArea inputTxt1 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp11 = new JScrollPane(inputTxt1); // 设置文本框中出现滚动条
	JTextArea outputTxt1 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp21 = new JScrollPane(outputTxt1); // 设置文本框中出现滚动条
	//语法分析文本框
	JTextArea inputTxt2 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp12 = new JScrollPane(inputTxt2); // 设置文本框中出现滚动条
	JTextArea outputTxt2 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp22 = new JScrollPane(outputTxt2); // 设置文本框中出现滚动条
	//语义分析文本框
	JTextArea inputTxt3 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp13 = new JScrollPane(inputTxt3); // 设置文本框中出现滚动条
	JTextArea outputTxt3 = new JTextArea(10, 30); // 设置本框中大小
	JScrollPane scp23 = new JScrollPane(outputTxt3); // 设置文本框中出现滚动条
	//设置表格
	ResultTableModel analysisResult;
	JTable resultTable;
	JScrollPane jScrollPane;
	//标签
	JLabel jLabel1 = new JLabel("输入");
	JLabel jLabel2 = new JLabel("分析结果");
	JLabel jLabel3 = new JLabel("输入");
	JLabel jLabel4 = new JLabel("三地址代码&错误分析");

	//选择器
	JFileChooser jFileChooser = new JFileChooser();
	//渲染器
	DefaultTableCellRenderer right = new DefaultTableCellRenderer() {
		
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			
	        setHorizontalAlignment(RIGHT);  
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		} 
	};
	DefaultTableCellRenderer center = new DefaultTableCellRenderer() {
		
		public Component getTableCellRendererComponent(JTable table, Object value, 
				boolean isSelected, boolean hasFocus, int row, int column) {
			
	        setHorizontalAlignment(CENTER);  
			return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); 
		} 
	}; 
	
	//工具条
	private JToolBar jToolBar1 = new JToolBar();
	private JToolBar jToolBar2 = new JToolBar();
	private JToolBar jToolBar3 = new JToolBar();
	
	//设置按钮
	private JButton inputFile1 = new JButton("导入文件");
	private JButton analyze1 = new JButton("分析");
	private JButton reset1 = new JButton("重置");
	private JButton inputFile2 = new JButton("导入文件");
	private JButton analyze2 = new JButton("分析");
	private JButton reset2 = new JButton("重置");
	private JButton inputFile3 = new JButton("导入文件");
	private JButton analyze3 = new JButton("分析");
	private JButton reset3 = new JButton("重置");
	
	//设置中间容器
	JPanel jPanel1 = new JPanel();		
	JPanel jPanel2 = new JPanel();
	JPanel jPanel3 = new JPanel();
	
	//设置卡容器
	JTabbedPane jTabbedPane = new JTabbedPane();
	
	StringBuffer outputDatas = new StringBuffer();
	
	static Lexer lex;
	static Parser parse;
	
	public AnalyzerView() {
		
		this.setTitle("编译器");
		this.setSize(WIDTH,HEIGTH);
		
		this.setContentPane(jTabbedPane);
		
		initTabbedPane();
		initPanel1();
		initPanel2();
		initPanel3();
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//将窗口显示在屏幕中间
		this.setLocationRelativeTo(null);
		this.setResizable(false);	
	}
	
	//初始化卡容器
	void initTabbedPane(){
		
		jTabbedPane.setTabPlacement(JTabbedPane.TOP);
		jTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
		jTabbedPane.addTab("词法分析", jPanel1);
		jTabbedPane.addTab("语法分析", jPanel2);
		jTabbedPane.addTab("语义分析", jPanel3);
		
		jTabbedPane.setEnabledAt(0, true);
		jTabbedPane.setEnabledAt(1, true);
		jTabbedPane.setEnabledAt(2, true);
		
		jPanel1.setLayout(null);
		jPanel2.setLayout(null);
		jPanel3.setLayout(null);
		
	}
	
	//初始化容器1
	void initPanel1(){
		//设置工具条
		jPanel1.add(jToolBar1);
		jToolBar1.setFloatable(false);
		jToolBar1.setBounds(0, 0, 1024, 30);
		//设置按钮
		jToolBar1.add(inputFile1);
		jToolBar1.add(analyze1);
		jToolBar1.add(reset1);
		//设置按钮监听
		inputFile1.addActionListener(this);
		analyze1.addActionListener(this);
		reset1.addActionListener(this);
		//设置按钮字体
		inputFile1.setFont(new Font("楷体",Font.PLAIN,15));
		analyze1.setFont(new Font("楷体",Font.PLAIN,15));
		reset1.setFont(new Font("楷体",Font.PLAIN,15));
		//向容器中添加文本框
		jPanel1.add(scp11);
		jPanel1.add(scp21);
		scp11.setBounds(40,85,450,550);
		scp21.setBounds(530,85,450,550);
		//设置标签
		jPanel1.add(jLabel1);
		jPanel1.add(jLabel2);
		jLabel1.setFont(new Font("楷体",Font.PLAIN,15));
		jLabel2.setFont(new Font("楷体",Font.PLAIN,15));
		jLabel1.setBounds(40, 50, 100, 25);
		jLabel2.setBounds(530, 50, 100, 25);
	}
	
	//初始化容器2
	void initPanel2(){
		//设置工具条
		jPanel2.add(jToolBar2);
		jToolBar2.setFloatable(false);
		jToolBar2.setBounds(0, 0, 1024, 30);
		//设置按钮
		jToolBar2.add(inputFile2);
		jToolBar2.add(analyze2);
		jToolBar2.add(reset2);
		//设置按钮监听
		inputFile2.addActionListener(this);
		analyze2.addActionListener(this);
		reset2.addActionListener(this);
		//设置按钮字体
		inputFile2.setFont(new Font("楷体",Font.PLAIN,15));
		analyze2.setFont(new Font("楷体",Font.PLAIN,15));
		reset2.setFont(new Font("楷体",Font.PLAIN,15));
		//向容器中添加文本框
		jPanel2.add(scp12);
		jPanel2.add(scp22);
		scp12.setBounds(40,50,450,200);
		scp22.setBounds(530,50,450,200);
		//设置表格
		ResultTableModel.initResultLength(150);
		analysisResult = new ResultTableModel();
		resultTable = new JTable(analysisResult);
		jScrollPane = new JScrollPane(resultTable);
		//向容器中添加表格
		jPanel2.add(jScrollPane);
		jScrollPane.setBounds(0, 275, WIDTH - 5, 420);
		//设置表格列宽
		resultTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		resultTable.getColumnModel().getColumn(0).setPreferredWidth(40);
		resultTable.getColumnModel().getColumn(1).setPreferredWidth(380);
		resultTable.getColumnModel().getColumn(2).setPreferredWidth(172);
		resultTable.getColumnModel().getColumn(3).setPreferredWidth(250);
		resultTable.getColumnModel().getColumn(4).setPreferredWidth(160);
		//设置列对齐方式
		resultTable.getColumnModel().getColumn(0).setCellRenderer(center);
		resultTable.getColumnModel().getColumn(3).setCellRenderer(right);
		resultTable.getColumnModel().getColumn(4).setCellRenderer(center);				
	}
	
	//初始化容器3
	void initPanel3(){
		//设置工具条
		jPanel3.add(jToolBar3);
		jToolBar3.setFloatable(false);
		jToolBar3.setBounds(0, 0, 1024, 30);
		//设置按钮
		jToolBar3.add(inputFile3);
		jToolBar3.add(analyze3);
		jToolBar3.add(reset3);
		//设置按钮监听
		reset3.addActionListener(this);
		inputFile3.addActionListener(this);
		analyze3.addActionListener(this);
		//设置按钮字体
		inputFile3.setFont(new Font("楷体",Font.PLAIN,15));
		analyze3.setFont(new Font("楷体",Font.PLAIN,15));
		reset3.setFont(new Font("楷体",Font.PLAIN,15));
		//向容器中添加文本框
		jPanel3.add(scp13);
		jPanel3.add(scp23);
		scp13.setBounds(40,85,450,550);
		scp23.setBounds(530,85,450,550);
		//设置标签
		jPanel3.add(jLabel3);
		jPanel3.add(jLabel4);
		jLabel3.setFont(new Font("楷体",Font.PLAIN,15));
		jLabel4.setFont(new Font("楷体",Font.PLAIN,15));
		jLabel3.setBounds(40, 50, 100, 25);
		jLabel4.setBounds(530, 50, 200, 25);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		String click = e.getActionCommand();
		switch(click){
		case "导入文件":
			jFileChooser.setDialogTitle("选择文件");
			int choose = jFileChooser.showOpenDialog(new JFrame());
			if(choose == JFileChooser.APPROVE_OPTION){	
				
				file = jFileChooser.getSelectedFile();
				showFile();
			}
			break;
		case "分析" :
			reset();
			String data = null;
			if (!inputTxt1.getText().equals("")) {
				data = inputTxt1.getText();
			} else if (!inputTxt2.getText().equals("")) {
				data = inputTxt2.getText();
			} else {
				data = inputTxt3.getText();
			}
			String[] dataSep = data.split("\n");
			lex.setDataSep(dataSep);
			parse.analysis(lex);
			outputDatas.append(lex.getOutput().toString());
			outputDatas.append(lex.getSymbol().toString());
			outputDatas.append(lex.getIdDatas().toString());
			outputTxt1.setText(outputDatas.toString());
			outputTxt2.setText(outputDatas.toString());
			outputTxt3.setText(parse.getTacBuff().toString());
		
			analysisResult.fireTableDataChanged();
			break;
		case "重置" :
			inputTxt1.setText("");
			outputTxt1.setText("");
			inputTxt2.setText("");
			outputTxt2.setText("");
			inputTxt3.setText("");
			outputTxt3.setText("");
			reset();
			break;
		}
	}
	
	void reset() {
		outputDatas.delete(0, outputDatas.length());
		lex.resetData();
		parse.initDatas();
		SDT.resetData();
		ResultTableModel.resetObject();
		analysisResult.fireTableDataChanged();
	}
	
	//文件输出到文本框
	void showFile(){
		
		FileInputStream fileInputStream = null;
		
		if(file != null){
				
			try {
				fileInputStream = new FileInputStream(file);
			} catch (FileNotFoundException e1) {
					
				//e1.printStackTrace();
			}
		}
		
		int readbyte;
		
		try{
			while((readbyte = fileInputStream.read()) != -1){
				
				inputTxt1.append(String.valueOf((char)readbyte));
				inputTxt2.append(String.valueOf((char)readbyte));
			}
		}catch(IOException ioe){		
	
		}				
		finally{
				
			try{
				if(fileInputStream != null)fileInputStream.close();
			}catch(IOException ioe2){
					
			}
		}
	}
			
	public static void main(String[] args) throws IOException{
		lex = new Lexer();
		parse = new Parser();
		
		new AnalyzerView();
	}

}

