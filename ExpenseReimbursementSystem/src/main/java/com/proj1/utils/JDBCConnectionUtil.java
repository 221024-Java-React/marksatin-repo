package com.proj1.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnectionUtil {

	private static JDBCConnectionUtil util;
	private static Properties props = new Properties();
	
	private JDBCConnectionUtil() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream inStream = classLoader.getResourceAsStream("jdbc.properties");
		
		try {
			props.load(inStream);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static JDBCConnectionUtil getInstance() {
		if (util == null) {
			util = new JDBCConnectionUtil();
		}
		
		return util;
	}
	
	public Connection getConnection() {
		Connection dbCon = null;
		
		try {
			String url = props.getProperty("url");
			String username = props.getProperty("username");
			String password = props.getProperty("password");
			
			dbCon = DriverManager.getConnection(url, username, password);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbCon;
	}
	
}
