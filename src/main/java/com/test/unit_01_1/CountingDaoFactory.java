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
		//모든 DAO는 여전히 connectionMaker()에서 만들어지는 오브젝트를 DI 받는다.
		//생성자->수정자 메서드로 바꿈.
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
