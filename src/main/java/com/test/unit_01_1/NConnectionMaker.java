package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// N���� �������� connection ����� ���� 
		return null;
	}

}
