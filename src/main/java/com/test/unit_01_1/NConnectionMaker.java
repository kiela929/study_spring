package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
	SQLException {
		System.out.println("N사의 connection입니다.");

		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection c= DriverManager.getConnection(
				"jdbc:oracle:thin:@localhost:1521:orcl","scott","tiger");


		return c;
	}

}
