package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
	private ConnectionMaker connectionMaker;
	
	public UserDao(){
		connectionMaker= new DConnectionMaker();
		//상태를 관리하는 것이 아니니깐 한 번만 만들어서 인스턴스 변수에 저장해두고 메소드에서 사용하도록 한다. 
	}
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c = connectionMaker.makeConnection(); //인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀐다고 해도 메소드 이름이 변경될 걱정은 없다.
		
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
	}
	
	
	
	public User get(String id) throws ClassNotFoundException, SQLException{

		Connection c = connectionMaker.makeConnection(); //인터페이스에 정의된 메소드를 사용하므로 클래스가 바뀐다고 해도 메소드 이름이 변경될 걱정은 없다.
			
		
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
	


}
