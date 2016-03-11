package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public class DConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException,
			SQLException {
		// D사의 독자적인 방법으로 connection 코드 생성하기!
		return null;
	}

}
