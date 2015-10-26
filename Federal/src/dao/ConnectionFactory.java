package dao;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
	private static String url="jdbc:mysql://localhost:3306/FEDERAL";
	private static String user="root";
	private static String password="170709My";
	private static String driver="com.mysql.jdbc.Driver";
	
	private static Driver drv = null;
	public static synchronized Connection getConnection() throws Exception{
		
		
		try 
		{ 
		 if (drv == null) { 
			drv = (Driver) Class.forName(driver).newInstance();
			DriverManager.registerDriver(drv);
			Class.forName(driver);}
		} 
		catch (SQLException e){
			throw new RuntimeException(e);
		}
		
		return DriverManager.getConnection(url,user,password);
}
}
