package com.test.unit_01_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	
	@Bean
	public UserDao userDao(){
		UserDao userDao =new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
		//��� DAO�� ������ connectionMaker()���� ��������� ������Ʈ�� DI �޴´�.
		//������->������ �޼���� �ٲ�.
	}
	
	@Bean 
	public ConnectionMaker connectionMaker(){
		return new CountingConnectionMaker(realConnectionMaker());
	}
	
	@Bean
	public ConnectionMaker realConnectionMaker(){
		return new DConnectionMaker();
	}

}
