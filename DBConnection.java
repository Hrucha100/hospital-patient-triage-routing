package com.kiranacademy.hospital;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private String driverClass = "com.mysql.cj.jdbc.Driver";
	private String databaseUrl = "jdbc:mysql://localhost:3306/";
	private  String databaseName = "hospital_task";
	private  String user = "root";
	public  String password="sql123";
	private Connection connection=null;
	
	
	public Connection getConnection() {
		
		try {
			Class.forName(driverClass);
			connection = DriverManager.getConnection(databaseUrl+databaseName,user,password);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			System.out.println("Connection Establish Sucessfully!!!"
					+ "");
		}
		 return connection;
		
	}
	
	
	

}
