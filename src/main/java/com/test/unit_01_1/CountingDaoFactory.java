package com.test.unit_01_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CountingDaoFactory {
	
	@Bean
	public UserDao userDao(){
		return new UserDao(connectionMaker());
		//��� DAO�� ������ connectionMaker()���� ��������� ������Ʈ�� DI �޴´�.
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
