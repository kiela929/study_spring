package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// D���� �������� ������� connection �ڵ� �����ϱ�!
		return null;
	}

}
