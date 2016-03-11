package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class NConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// N사의 독자적인 connection 방법을 구현 
		return null;
	}

}
