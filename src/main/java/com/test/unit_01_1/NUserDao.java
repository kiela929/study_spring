package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDao extends UserDao{
	
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		
		//N사에 맞는 connection을 구현한다. 
		return null;
	}

}
