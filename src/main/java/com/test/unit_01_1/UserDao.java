package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class UserDao {
	
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c = getConnection(); //중복코드를 메소드로 만들어서 호출함.
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{

		Connection c = getConnection(); //중복코드를 메소드로 만들어서 호출함.
		
		
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id=?");
		ps.setString(1,id);
		
		ResultSet rs=ps.executeQuery();
		
		rs.next();
		User user = new User();
		user.setId(rs.getString("id"));
		user.setName(rs.getString("name"));
		user.setPassword(rs.getString("password"));
		
		
		rs.close();
		ps.close();
		c.close();
		
		return user;
	}
	
	public abstract Connection getConnection() throws ClassNotFoundException, SQLException;
		
		/**
		 * 수현코드는 제거되고 추상메소드로 바뀌었다. 메소드의 구현은 서브클래스가 한다. 
		 * 즉, 이 추상메서드를 오버라이드 하는 곳에서 원하는 내용을 적는다는 것!
		 */
	

}
