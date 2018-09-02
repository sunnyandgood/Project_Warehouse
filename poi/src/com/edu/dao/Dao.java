package com.edu.dao;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.edu.bean.Question;
import com.edu.dbutil.DbUtils;

public class Dao {
	public Object resultToList(ResultSet resultSet,Class<?> classs){
		//用来封装实体类对象
		List<Object> list=new ArrayList<Object>();
		try {
			while(resultSet.next()){
				//是用哪个反射机制创建实体类对象
				Object entity=classs.newInstance();
				for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
					//根据索引获取当前字段名称
					String columnName=resultSet.getMetaData().getColumnName(i);
					//获取当前索引的值
					Object value=resultSet.getObject(i);
					//根据字段名称获得当前类中的单一属性
					Field field=classs.getDeclaredField(columnName);
					//将当前属性设置为可赋值状态
					field.setAccessible(true);
					//为指定对象的舒心进行赋值
					field.set(entity, value);
				}
				list.add(entity);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}		
		return list;
	}
	
	public int insert(String sql,String[] values) throws Exception{
		int i = 0;
     
		DbUtils dbUtils = new DbUtils();
		Connection connection = dbUtils.getConnection();
		PreparedStatement prepareStatement = connection.prepareStatement(sql);
		if(values != null) {
			for(int j=0;j<values.length;j++) {
				prepareStatement.setString(j+1, values[j]);
			}
		}
		i = prepareStatement.executeUpdate();
		return i;
   }
}
