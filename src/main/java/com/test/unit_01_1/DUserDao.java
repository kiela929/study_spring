package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao{
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		//D사에 맞는 Connection을 구현한다.
		return null;
	}

}
