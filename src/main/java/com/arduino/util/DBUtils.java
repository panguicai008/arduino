package com.arduino.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {

	private static String url="jdbc:mysql://localhost:3306/arduino";  //
	private static Connection conn;
	static{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(url, "root","student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection openConnection(){
		if(conn==null)
			try {
				conn=DriverManager.getConnection(url, "root","student");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return conn;
	}
	
	
	public static void close(){
		try{
			if(conn!=null&&!conn.isClosed()){conn.close();conn=null;}
		}catch(Exception e){
			e.printStackTrace();
			conn=null;
		}
	}
}
