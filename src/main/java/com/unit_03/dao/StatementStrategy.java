package com.unit_03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface StatementStrategy {
	
	PreparedStatement makePreparedStatement(Connection c) throws SQLException;
	//실질적인 SQL문을 작성할 추상메서드. 
	//insert,delete 등 다양한 작업에서 사용하는 SQL문이 항상 바뀌기 때문에 추상메서드로 관리함. 
	//즉, 전략클래스의 조상이라고 생각합시다.

}
