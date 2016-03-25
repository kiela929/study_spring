package com.unit_03.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;


@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시이다. 
public class DaoFactory {
	
	@Bean
	public DataSource dataSource(){
		SimpleDriverDataSource dataSource=new SimpleDriverDataSource();

		
		return dataSource;
		
	}
	@Bean //오브젝트 생성을 담당하는 IoC용 메소드라는 표시이다. 
	public UserDao userDao(){
		UserDao userDao =new UserDao();
		userDao.setDataSource(dataSource());
//		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	

}
