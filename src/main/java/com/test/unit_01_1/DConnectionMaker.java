package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// D���� �������� ������� connection �ڵ� �����ϱ�!
		System.out.println("D���� connection�Դϴ�.");
		
		Class.forName("oracle.jdbc.driver.OracleDriver");
		 		Connection c= DriverManager.getConnection(
		 				"jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");
		
		
		return c;
	}

}
