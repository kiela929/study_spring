package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class NUserDao extends UserDao{
	
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		
		//N�翡 �´� connection�� �����Ѵ�. 
		return null;
	}

}
