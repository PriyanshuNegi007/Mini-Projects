package javaATMProject;

import java.sql.*;

public class DatabaseConnection {
	
	private static final String URL ="jdbc:mysql://localhost:3306/atm_db";
	
	private static final String USER ="root";
	
	private static final String PASSWORD ="Negi@123";
	
	public static Connection getConnection() throws SQLException{
		return DriverManager.getConnection(URL,USER,PASSWORD);
		
	}

}
