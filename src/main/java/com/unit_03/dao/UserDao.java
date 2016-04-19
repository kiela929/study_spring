package com.unit_03.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

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
		
		return this.jdbcTemplate.query(
				new PreparedStatementCreator() { //첫번째 콜백, 템플릿으로부터 Connection을 받고 PreparedStatement를 돌려받음.
			public PreparedStatement createPreparedStatement (Connection con) throws SQLException{
				return con.prepareStatement("select count(*) from users");
			}
		},new ResultSetExtractor<Integer>(){//두 번째 콜백, 템플릿으로부터 ResultSet을 받고 여기서 만들어진 결과를 돌려줌
			public Integer extractData(ResultSet rs) throws SQLException{
				rs.next();
				return rs.getInt(1);
			}
		});
/*
		Connection c = dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement("select count(*) from users");

		ResultSet rs=ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		rs.close();
		ps.close();
		c.close();

		return count;*/
	}



	
	
	public User get(String id) throws ClassNotFoundException, SQLException{
		
		return this.jdbcTemplate.queryForObject("select * from users where id=?",
				new Object[] {id},//sql에 바인딩할 파라미터 값. 가변인자 대신 배열을 사용한다. 
				new RowMapper<User>(){//결과 값을 만든 User오브젝트에 매핑해줌!
			public User mapRow(ResultSet rs, int rowNum)
				throws SQLException{
				User user=new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		
//		if(user==null) throw new EmptyResultDataAccessException(1);
		//데이터가 없을 경우 다음과 같은 익셉션을 던져줘~
		//이 부분은 기존 템플릿,콜백을 만들었을때 처리해줬던 예외처리이다. 
		//따로 써주지 않아도 queryForObject()는 데이터가 없을 경우 같은 예외를 던져주니 써줄 필요 없다.  
		
	}
}
