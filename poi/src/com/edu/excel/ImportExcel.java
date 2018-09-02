package com.edu.excel;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.edu.bean.Question;
import com.edu.dbutil.DbUtils;

public class ImportExcel {
	public static String[][] getValuesFromExcel(InputStream inputStream) throws Exception{
		//创建工作簿
		Workbook workbook =WorkbookFactory.create(inputStream);
		//创建一个sheet
		Sheet sheet = workbook.getSheet("Sheet0");
		
		int rowNum = sheet.getLastRowNum();
		
		String[][] values = new String[rowNum+1][sheet.getRow(0).getLastCellNum()+1];
		for(int i=0;i<=rowNum;i++){
			Row row = sheet.getRow(i);
			int cellNum = row.getLastCellNum();
			for(int j=0;j<cellNum;j++){
				Cell cell=row.getCell(j);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				String value = null;
//				if(!(cell.getStringCellValue().equals(""))) {
					value=cell.getStringCellValue();
//				}
				
				System.out.println(value);
				values[i][j] = value;
			}
		}
//		System.out.println(values);
		return values;
	}
	public static void main(String[] args) throws Exception {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		InputStream inputStream = new FileInputStream("D:/qq.xls");
		
		String[][] values = getValuesFromExcel(inputStream);
		List<Question> list = new ArrayList<>();
		
		for(int i=1;i<values.length;i++) {
			Question question = new Question();
			if(values[i][0]!=null) {
				question.setQuestionId(Integer.parseInt(values[i][0]));
			}
			question.setQuestionChapter(values[i][1]);
			if(values[i][2]!=null) {
				question.setQuestionDifficult(Integer.parseInt(values[i][2]));
			}
			question.setQuestionTitle(values[i][3]);
			if(values[i][4]!=null && !(values[i][4].equals(""))) {
				System.out.println(values[i][4] + " 00:00:00");
				question.setQuestionTime(dateFormat.parse(values[i][4] + " 00:00:00"));//+" 00:00:00.000"
//				question.setQuestionTime(values[i][4]);			
			}
			question.setQuestionAuthor(values[i][5]);
			question.setQuestionAnswer(values[i][6]);
			question.setQuestionPara1(values[i][7]);
			question.setQuestionPara2(values[i][8]);
			question.setQuestionPara3(values[i][9]);
			list.add(question);
		}
		
		for(Question question : list) {
			String format = dateFormat.format(question.getQuestionTime());
			String sql = "insert into question (question_id,question_chapter,question_difficult,"
					+ "question_title,question_time,question_author,question_answer,question_para1,"
					+ "question_para2,question_para3)"
					+ " values ('"+question.getQuestionId()+"','"+question.getQuestionChapter()+"',"
							+ "'"+question.getQuestionDifficult()+"','"+question.getQuestionTitle()+"',"
							+ "'"+format+"','"+question.getQuestionAuthor()+"',"
							+ "'"+question.getQuestionAnswer()+"','"+question.getQuestionPara1()+"',"
							+ "'"+question.getQuestionPara2()+"','"+question.getQuestionPara3()+"')";
			DbUtils dbUtils = new DbUtils();
			Statement statement = dbUtils.getStatement();
			int insert = statement.executeUpdate(sql);
			dbUtils.close();
			System.out.println(insert);
		}
	}
}
