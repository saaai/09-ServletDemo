


package com.egiants.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServletUtil {
	
public static Connection getConnection ()//making this method as public enables it to be accessed by MyServlet4.java file while executing
	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");//registering driver
			//creating connection
			try {
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test3","root", "hello");
				System.out.println("Connection Established");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;//if anybody calling the getConnection() they will get the conn object
	}

}
