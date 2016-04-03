package com.unit_03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

public class JdbcContext {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
	}
	
	
	public void workWithStatementStrategy(StatementStrategy stmt) throws SQLException{
		
		Connection c = null;
		PreparedStatement ps= null;
		try {
			c = dataSource.getConnection();
			ps=stmt.makePreparedStatement(c);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;

		}finally{
			if(ps !=null){try{ps.close();} catch(SQLException e){}}
			if(c!=null){try{ps.close();} catch(SQLException e){}}
		}
	}
	
	public void executeSql(final String query) throws SQLException{
		workWithStatementStrategy(
				new StatementStrategy() { //StatementStrategy는 원래 구현해야할 인터페이스이름.
					//그러나 익명 내부 클래스는 구현하는 인터페이스를 생성자처럼 사용해서 오브젝트로 만든다!! 

					public PreparedStatement makePreparedStatement(Connection c)
							throws SQLException {

						return c.prepareStatement(query);
					}
					//변하지 않는 콜백 클래스 정의와 오브젝트 생성

				});
	}

}
