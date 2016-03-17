package com.test.unit_01_1;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


@Configuration //���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ����������� ǥ���̴�. 
public class DaoFactory {
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource=new SimpleDriverDataSource();

		dataSource.setDriverClass(oracle.jdbc.driver.OracleDriver.class);
		dataSource.setUrl("jdbc:oracle:thin:@localhost:1521:orcl");
		dataSource.setUsername("scott");
		dataSource.setPassword("tiger");
		
		return dataSource;
		
	}
	@Bean //������Ʈ ������ ����ϴ� IoC�� �޼ҵ��� ǥ���̴�. 
	public UserDao userDao(){
		UserDao userDao =new UserDao();
		userDao.setDataSource(dataSource());
//		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}

}
