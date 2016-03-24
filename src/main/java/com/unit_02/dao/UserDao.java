package com.unit_02.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.unit_02.domain.User;

public class UserDao {
/*	private ConnectionMaker connectionMaker;
	
	public UserDao(ConnectionMaker connectionMaker){
		this.connectionMaker=connectionMaker;
		//userDao를 사용하려면 생성자를 이용해 N사든 D사든 원하는 것으로 만들면 
		//사용 할 수 있다. 즉, 현재 이 클래스는 어느 회사의 connection을 쓸지에는 관심 없다. 
		//단지 connection을 쓴다는 것 자체에만 관심있다는 것!! 
		//어떤것을 사용할지는 클라이언트에게 맡기는 것! 이렇게 해야 의존관계가 제대로 성립된다. 

	}
	
	public void setConnectionMaker(ConnectionMaker connectionMaker){
		this.connectionMaker=connectionMaker;
		// 생성자를 사용하는 것 보다 이렇게 수정자 메서드를 이용하는 것이 훨씬 더 편리한 방법이다. 
		//생성자는 한번만 사용가능하지만, 메서드는 사용횟수에 제한을 받지않는다.
	}*/
	
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource){
		this.dataSource=dataSource;
	}
	public void add(User user) throws ClassNotFoundException, SQLException{
		
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.executeUpdate();
		
		ps.close();
		c.close();
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
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();
		
		ps.close();
		c.close();
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
	


}
