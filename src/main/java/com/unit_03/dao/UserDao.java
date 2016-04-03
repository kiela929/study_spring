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
	private JdbcContext jdbcContext; 

	public void setDataSource(DataSource dataSource){
		this.jdbcContext=new JdbcContext();
		this.jdbcContext.setDataSource(dataSource);
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


		this.jdbcContext.workWithStatementStrategy(
				new StatementStrategy() { //�͸� ���� Ŭ������ �����ϴ� �������̽��� ������ó�� �̿��ؼ� ������Ʈ�� �����. 
					public PreparedStatement makePreparedStatement(Connection c)
							throws SQLException {

						PreparedStatement ps =
								c.prepareStatement("insert into users(id,name,password)values(?,?,?)");

						ps.setString(1, user.getId());
						ps.setString(2, user.getName());
						ps.setString(3, user.getPassword());

						return ps;
					}
				});

	}


	
	public void deleteAll() throws SQLException{ //�͸���Ŭ������ �ٲٱ�!!
		
		this.jdbcContext.executeSql("delete from users");
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
