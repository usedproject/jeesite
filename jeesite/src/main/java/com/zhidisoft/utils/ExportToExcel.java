package com.zhidisoft.utils;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import jxl.CellView;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
/**
 * 
 * @author 赵恒
 *
 * 2017年12月13日
 */
public class ExportToExcel {
	/*
	 * 读取jdbc配置文件
	 */
	static ResourceBundle bundle = ResourceBundle.getBundle("/jdbc");
	/*
	 * 加载数据库驱动
	 */
	static {
		try {
			Class.forName(bundle.getString("jdbc.driverClass"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 创建数据库的连接
	 * @return
	 */
	private static Connection getConn() {
		Connection connection = null;
		try {
			//connection = DriverManager.getConnection("jdbc:mysql://192.168.10.205:3306/jeesite", "root", "root");
			connection = DriverManager.getConnection(bundle.getString("jdbc.jdbcUrl"), bundle.getString("jdbc.user"), bundle.getString("jdbc.password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	
	

	/**
	 * 查询方法，返回List<HashMap>对象，HashMap中存储表的每一行记录
	 * 参数只有一个sql语句
	 * @param sql
	 * @return
	 */
	public static void query(String sql, Object[] obj2) {
		query(sql, obj2,null);
		
	}

	/**
	 * 查询方法，返回List<HashMap>对象，HashMap中存储表的每一行记录
	 * 参数为sql语句和相对应的Object数组
	 * @param sql
	 * @return
	 */

	public static void query(String sql, Object[] obj2, Object[] obj) {
		// 获取数据库连接
		Connection conn = getConn();
		// 创建执行对象
		PreparedStatement ps = null;
		WritableWorkbook workBook = null;
		WritableSheet sheet = null;
		try {
			ps = conn.prepareStatement(sql);
			if (obj != null && obj.length > 0) {
				for (int i = 0; i < obj.length; i++) {
					ps.setObject(i + 1, obj[i]);
				}
			}
			// 执行SQL语句，返回值为ResultSet对象
			ResultSet rs = ps.executeQuery();
			// 获取表的结构
			ResultSetMetaData metaData = rs.getMetaData();
			// 获得表的字段个数
			int count = metaData.getColumnCount();
			
			//创建Vector用来保存列名，并将列名存储到vector中
			Vector<String> vector = new Vector<>();
			for(int i=0; i<count;i++) {
				//vector.add(rs.getMetaData().getColumnLabel(1+i));
				vector.add(obj2[i].toString());
			}
			
			//创建文件选择器对话框
			JFileChooser jfc = new JFileChooser();
			//设定该对话框问文件与目录
			jfc.addChoosableFileFilter(new ExcelFileFiter());
			jfc.addChoosableFileFilter(new WordFilter());
			int k = jfc.showSaveDialog(null);
			//当用户点击确认按钮时，执行以下操作
			if( k== JFileChooser.APPROVE_OPTION) {
				//读取选择的文件，该文件为当前文件夹和输入的文件名
				File file = jfc.getSelectedFile();
				//判断文件过滤器的类别
				FileFilter fileFilter = jfc.getFileFilter();
				//获取文件过滤器的后缀名
				String description = fileFilter.getDescription();
				//再创建一个文件
				File newFile;
				//如果用户输入的文件名和文件过滤器的后缀重复，则仍以该文件作为对象，否则自动的添加到末尾
				if(file.getName().endsWith(description)) {
					newFile = file;
				} else {
					newFile = new File(file.getAbsolutePath()+description);
				}
				//读取该Excel文件，创建表格对象，即WorkBook类对象
				workBook = Workbook.createWorkbook(newFile);
				//创建工作簿
				sheet = workBook.createSheet("人员表", 0);
				//设置单元格样式
				WritableCellFormat cellFormat = new WritableCellFormat();
				//设置字体样式
				WritableFont font = new WritableFont(WritableFont.createFont("微软雅黑"), 12, 
						WritableFont.NO_BOLD, false, UnderlineStyle.NO_UNDERLINE, Colour.BLACK);
				cellFormat.setFont(font);
				cellFormat.setAlignment(Alignment.LEFT);
				cellFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
				
				//设置每一列的最大宽度
				int max[] = new int[count];
				int rowNum = 0;
				//将vector中的数据写入sheet，从第0行第0列开始
				for(int i=0; i<vector.size(); i++) {
					max[i] = vector.get(i).length();
					Label label = new Label(i, rowNum, vector.get(i), cellFormat);
					sheet.setColumnView(i, max[i]+4);
					sheet.addCell(label);
				}
				//再将resultSet中的结果集写入到sheet中
				while(rs.next()) {
					//创建vector用来保存每一行的数据
					Vector<String> vector2 = new Vector<>();
					
					for(int i=0; i<count; i++) {
						vector2.add(rs.getString(i+1));
					}
					//再将vector中的数据集写到sheet中
					for(int i=0; i<vector2.size(); i++) {
						Label label = new Label(i, rowNum+1, vector2.get(i),cellFormat);
						if(vector2.get(i)!=null) {
							if(vector2.get(i).length()>max[i]) {
								max[i] = vector2.get(i).length();
							}
						}
						sheet.setColumnView(i, max[i]+4);
						sheet.addCell(label);
					}
					rowNum++;
				}
				workBook.write();
			}
			
			close(sheet, workBook, rs, ps, conn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (RowsExceededException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 释放资源，按照后创建，先关闭的原则
	 * 
	 * @param rs
	 * @param ps
	 * @param conn
	 */
	private static void close(WritableSheet sheet, WritableWorkbook workBook, ResultSet rs, PreparedStatement ps, Connection conn) {
		try {
			if(workBook!=null) {
				workBook.close();
			}
			if (rs != null) {
				rs.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (conn != null) {
				ps.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
class ExcelFileFiter extends FileFilter {

	@Override
	public boolean accept(File file) {
		String name = file.getName(); 
        return file.isDirectory() || name.toLowerCase().endsWith(".xls");  // 仅显示目录和xls、xlsx文件
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".xls";
	}
	
}

class WordFilter extends FileFilter{
	@Override
	public boolean accept(File file) {
		String name = file.getName(); 
        return file.isDirectory() || name.toLowerCase().endsWith(".doc");  // 仅显示目录和xls、xlsx文件
	}
	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return ".doc";
	}
}
