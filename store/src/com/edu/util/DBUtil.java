package com.edu.util;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBUtil {
	//设置数据库名称
    private static final String URL = "jdbc:sqlserver://localhost:1433;DatabaseName=yan";
    private static final String USER = "sa";
    private static final String PASSWORD = "root";
    
    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;
    private static DBUtil dbUtil = new DBUtil();
    
    //对外提供一个方法来获取数据库连接
    public Connection getConnection() {
		try {
			//1.加载驱动程序
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//2.获得数据库的连接
	        connection = (Connection)DriverManager.getConnection(URL,USER,PASSWORD);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
    }
    //返回一个Statement对象
    public Statement getStatement() {
    	try {
    		connection = dbUtil.getConnection();
    		//3.获得Statement对象
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return statement;
    }
    //返回一个ResultSet对象
    public ResultSet getResultSet(String sql){
        try {
        	statement = dbUtil.getStatement();
            //4.执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
            resultSet = statement.executeQuery(sql);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return resultSet;
    }
    
    //关闭连接
    public void close() {
		
		if(connection != null){
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
		
		if(statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			statement = null;
		}
		
		if(resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			resultSet = null;
		}
	}
    
    public Object resultToList(ResultSet resultSet,Class<?> classs){
		//用来封装实体类对象
		List<Object> list = new ArrayList<Object>();
		try {
			while(resultSet.next()){
				//是用哪个反射机制创建实体类对象
				Object entity = classs.newInstance();
				for(int i=1;i<=resultSet.getMetaData().getColumnCount();i++){
					//根据索引获取当前字段名称
					String columnName = resultSet.getMetaData().getColumnName(i);
					//获取当前索引的值
					Object value = resultSet.getObject(i);
					//根据字段名称获得当前类中的单一属性
					Field field = classs.getDeclaredField(columnName);
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
    
    //测试用例
    public static void main(String[] args) throws Exception{
        
//        //3.通过数据库的连接操作数据库，实现增删改查
//        Statement stmt = connection.createStatement();
//        //ResultSet executeQuery(String sqlString)：执行查询数据库的SQL语句   ，返回一个结果集（ResultSet）对象。
//        ResultSet rs = stmt.executeQuery("select * from store");
    	DBUtil dbUtil = new DBUtil();
    	ResultSet rs = dbUtil.getResultSet("select * from store");
        while(rs.next()){//如果对象中有数据，就会循环打印出来
            System.out.println(rs.getString("s_id")+","+rs.getString("s_name")+","+rs.getString("s_price")+","+rs.getString("s_picture"));
        }
    }
}
