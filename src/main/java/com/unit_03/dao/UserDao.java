package com.unit_03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.unit_03.domain.User;

public class UserDao {
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
	}
	public void add(User user) throws SQLException{
		StatementStrategy st= new AddStatement(user);
		jdbcContextWithStatementStrategy(st);
	}
	
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{

		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id=?");
		ps.setString(1,id);
		
		ResultSet rs=ps.executeQuery();
		
		User user =null;
		
		if(rs.next()){
			//만일 데이터가 없을 경우 SQLException이 일어나는 것을 방지하는 것.
		user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if(user==null) throw new EmptyResultDataAccessException(1);
		//데이터가 없을 경우 다음과 같은 익셉션을 던져줘~
		
		return user;
	}
	
	public void deleteAll() throws SQLException{
		StatementStrategy st = new DeleteAllStatement(); //전략 클래스의 오브젝트 생성
		jdbcContextWithStatementStrategy(st);//컨텍스트 호출! 전략오브젝트를 전달함.
	
	}
	
	public int getCount()throws SQLException{
		
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("select count(*) from users");
		
		ResultSet rs=ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);
		
		rs.close();
		ps.close();
		c.close();
		
		return count;
	}
	
	public void jdbcContextWithStatementStrategy(StatementStrategy stmt) throws
	SQLException{
		Connection c = null;
		PreparedStatement ps= null;
		try {
			c = dataSource.getConnection();
			ps=stmt.makePreparedStatement(c);
			ps.executeUpdate();

		} catch (SQLException e) {
			throw e;
			
		}finally{
			if(ps !=null){
				try{
					ps.close();
				} catch(SQLException e){
				}
			}
		if(c!=null){
			try{
				ps.close();
			} catch(SQLException e){
			}
		}
		}
	}


}
