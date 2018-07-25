package com.zhidisoft.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class ImportExcel {
	
	public static List<List<String>> load() {
		List<List<String>> list = new ArrayList<List<String>>();
		//创建文件选择器
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ExcelFileFiter());
		if(jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			File file = jfc.getSelectedFile();
			Workbook workbook = null;
			try {
				workbook = Workbook.getWorkbook(file);
				Sheet sheet = workbook.getSheet(0);
				int rows = sheet.getRows();
				int columns = sheet.getColumns();
				for(int i=1; i<rows;i++) {
					List<String> strList = new ArrayList<String>();
					boolean flag = false;
					for(int j=0; j<columns; j++) {
						Cell cell = sheet.getCell(j, i);
						String contents = cell.getContents();
						
						if(contents!=null && !contents.equals("")) {
							  flag = true;
							  strList.add(contents);
						} else {
							strList.add(contents);
						}
						
					}
					if(flag) {
						list.add(strList);
					}
				}
			
			} catch (BiffException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				workbook.close();
			}
		}
		return list;
		
	}
	
	public static Integer insert(List<List<String>> list, String sql) {
		for(List<String> strList : list) {
			Object[] obj = new Object[strList.size()];
			for(int i=0; i<strList.size(); i++) {
				obj[i] = strList.get(i);
			}
			DBUtil.update(sql, obj);
		}
		return 1;
	}
}
