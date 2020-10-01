package com.psl.java;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.opencsv.exceptions.CsvValidationException;

public class DatabaseConnection {
	String username = null;
	String password = null;
		
	public String database() throws Exception{
		final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
		final String DB_URL = "jdbc:mysql://localhost:3306/wordpress";
		
		//Database Credentials
		final String user = "root";
		final String pass = "";
		
		Connection conn = null;
		Statement stmt = null;
		
		//Register JDBC Driver
		Class.forName(JDBC_DRIVER);
		conn = DriverManager.getConnection(DB_URL, user, pass);
		
		//Execute a query
		stmt = conn.createStatement();
		
		String sql = "SELECT USER_LOGIN, USER_PASS FROM WORDPRESS.WP_USERS WHERE USER_LOGIN='administrator'";
		ResultSet executequery = stmt.executeQuery(sql);
		
		//Extract data from Result set
		while(executequery.next())
		{
			username = executequery.getString("user_login");
			password = executequery.getString("user_pass");
			System.out.println("DB Username : "+username);
			System.out.println("DB Password : "+password);
			
		}
		
		//close all connections
		executequery.close();
		stmt.close();
		conn.close();
		return username+"_"+password;
	}
	
}

	


