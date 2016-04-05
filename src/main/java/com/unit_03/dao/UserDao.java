package com.unit_03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;

import com.unit_03.domain.User;

public class UserDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate; 

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		this.dataSource=dataSource;
		/*
		 * jdbcContext를 xml의 빈 설정을 하지 않고, 내부에서 직접 만들어서 사용하는 방법!
		 * 
		 * 굳이 인터페이스를 두지 않아도 될 만큼 긴밀한 관계를 갖는 DAO클래스와
		 * JdbcContext를 어색하게 따로 빈으로 분리하지 않고 내부에서 직접 만들어 사용하면서도
		 * 다른 오브젝트에 대한 DI를 적용할 수 있다는 점이 장점이다. 
		 */
	}
	

	
	public void add(final User user) throws SQLException{ //익명내부클래스로 바꾸기!!


		this.jdbcTemplate.update("insert into users(id,name,password)values(?,?,?)",
				user.getId(),user.getName(),user.getPassword());
		/*
		 * JdbcTemplate를 사용하였기 때문에 각각의 파라메터는 대응되는 값을 적어주면 된다. 
		 */
		
	}


	
	public void deleteAll() throws SQLException{ //익명내부클래스로 바꾸기!!
		
		this.jdbcTemplate.update("delete from users");
		/*
		 * JdbcTemplate를 이용하면 update라는 함수를 사용하여 한 줄로 구현이 가능!
		 */
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
}
