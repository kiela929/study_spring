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

import com.unit_03.domain.User;

public class UserDao {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate; 

	public void setDataSource(DataSource dataSource){
		this.jdbcTemplate=new JdbcTemplate(dataSource);
		this.dataSource=dataSource;
		/*
		 * jdbcContext�� xml�� �� ������ ���� �ʰ�, ���ο��� ���� ���� ����ϴ� ���!
		 * 
		 * ���� �������̽��� ���� �ʾƵ� �� ��ŭ ����� ���踦 ���� DAOŬ������
		 * JdbcContext�� ����ϰ� ���� ������ �и����� �ʰ� ���ο��� ���� ����� ����ϸ鼭��
		 * �ٸ� ������Ʈ�� ���� DI�� ������ �� �ִٴ� ���� �����̴�. 
		 */
	}
	

	
	public void add(final User user) throws SQLException{ //�͸���Ŭ������ �ٲٱ�!!


		this.jdbcTemplate.update("insert into users(id,name,password)values(?,?,?)",
				user.getId(),user.getName(),user.getPassword());
		/*
		 * JdbcTemplate�� ����Ͽ��� ������ ������ �Ķ���ʹ� �����Ǵ� ���� �����ָ� �ȴ�. 
		 */
		
	}


	
	public void deleteAll() throws SQLException{ //�͸���Ŭ������ �ٲٱ�!!
		
		this.jdbcTemplate.update("delete from users");
		/*
		 * JdbcTemplate�� �̿��ϸ� update��� �Լ��� ����Ͽ� �� �ٷ� ������ ����!
		 */
	}



	public int getCount()throws SQLException{
		
		return this.jdbcTemplate.query(
				new PreparedStatementCreator() { //ù��° �ݹ�, ���ø����κ��� Connection�� �ް� PreparedStatement�� ��������.
			public PreparedStatement createPreparedStatement (Connection con) throws SQLException{
				return con.prepareStatement("select count(*) from users");
			}
		},new ResultSetExtractor<Integer>(){//�� ��° �ݹ�, ���ø����κ��� ResultSet�� �ް� ���⼭ ������� ����� ������
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
		
		Connection c = dataSource.getConnection();
		
		PreparedStatement ps = c.prepareStatement(
				"select * from users where id=?");
		ps.setString(1,id);
		
		ResultSet rs=ps.executeQuery();
		
		User user =null;
		
		if(rs.next()){
			//���� �����Ͱ� ���� ��� SQLException�� �Ͼ�� ���� �����ϴ� ��.
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}
		
		rs.close();
		ps.close();
		c.close();
		
		if(user==null) throw new EmptyResultDataAccessException(1);
		//�����Ͱ� ���� ��� ������ ���� �ͼ����� ������~
		
		return user;
	}
}
