package com.edu.excel;

import java.io.FileOutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.edu.bean.Question;
import com.edu.dao.Dao;
import com.edu.dbutil.DbUtils;

/**
 * 导出excel公共方法
 * @author sunny
 */
public class ExportExcel  {
	public static HSSFWorkbook getHSSFWorkbook(ResultSet resultSet,String[][] values) {
		//创建工作簿
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建一个sheet
		HSSFSheet sheet = workbook.createSheet();
		//创建单元格样式
		HSSFCellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setAlignment(cellStyle.);
		
		//在sheet中添加表头第0行
		HSSFRow row = sheet.createRow(0);
		//声明列对象
		HSSFCell cell = null;
		
		//创建标题
		try {
			for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
				//根据索引获取当前字段名称
				String columnName=resultSet.getMetaData().getColumnName(i);
				cell = row.createCell(i-1);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(columnName);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//创建内容
		for(int i=0;i<values.length;i++) {
			row = sheet.createRow(i+1);
			for(int j=0;j<values[i].length;j++) {
				//将内容按顺序赋给对应的列对象
				row.createCell(j).setCellValue(values[i][j]);
			}
		}
		
		return workbook;
	}
	
	public static void main(String[] args) throws Exception {
		DbUtils dbUtils = new DbUtils();
		Dao dao = new Dao();
		String sql = "select * from question";
		ResultSet resultSet = dbUtils.getResultSet(sql);
		@SuppressWarnings("unchecked")
		List<Question> list = (List<Question>) dao.resultToList(resultSet, Question.class);
//		System.out.println(list);
		String[][] values = new String[list.size()][resultSet.getMetaData().getColumnCount()];
//		System.out.println(resultSet.next());
		for(int i=0;i<list.size();i++) {
//			System.out.println(list.get(i)+"head");
			values[i][0] = list.get(i).getQuestionId().toString();
			values[i][1] = list.get(i).getQuestionChapter();
			values[i][2] = list.get(i).getQuestionDifficult().toString();
			values[i][3] = list.get(i).getQuestionTitle();
			if(list.get(i).getQuestionTime()!=null) {
				values[i][4] = list.get(i).getQuestionTime().toString();
			}
			values[i][5] = list.get(i).getQuestionAuthor();
			values[i][6] = list.get(i).getQuestionAnswer();
			values[i][7] = list.get(i).getQuestionPara1();
			values[i][8] = list.get(i).getQuestionPara2();
			values[i][9] = list.get(i).getQuestionPara3();
			System.out.println(list.get(i));
		}
		
		FileOutputStream fileOutputStream = new FileOutputStream("D:/aa.xls");
		HSSFWorkbook hssfWorkbook = getHSSFWorkbook(resultSet,values);
		hssfWorkbook.write(fileOutputStream);
		fileOutputStream.close();
	}
}
