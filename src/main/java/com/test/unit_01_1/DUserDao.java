package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class DUserDao extends UserDao{
	@Override
	public Connection getConnection() throws ClassNotFoundException,
			SQLException {
		//D�翡 �´� Connection�� �����Ѵ�.
		return null;
	}

}
