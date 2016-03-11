package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	
	public Connection makeConnection() throws ClassNotFoundException, SQLException;

}
