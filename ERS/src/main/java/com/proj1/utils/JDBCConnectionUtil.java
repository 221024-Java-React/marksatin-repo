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
	
	private JDBCConnectionUtil() {}
	
	public static JDBCConnectionUtil getInstance() {
		if (util == null) {
			util = new JDBCConnectionUtil();
		}
		
		System.out.println("util: " + util);
		return util;
	}
	
	
	
	public Connection getConnection() {
		Connection dbCon = null;
		System.out.println("dbCon initial: " + dbCon);
		
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			InputStream inStream = classLoader.getResourceAsStream("jdbc.properties");
			
			System.out.println("inStream: " + inStream);
			
			String url = "";
			String username = "";
			String password = "";
			
			props.load(inStream);
			
			dbCon = DriverManager.getConnection(url, username, password);
			System.out.println("dbCon updated: " + dbCon);
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dbCon;
	}
	
}
