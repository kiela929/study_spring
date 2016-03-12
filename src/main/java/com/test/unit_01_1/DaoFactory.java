package com.test.unit_01_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표시이다. 
public class DaoFactory {
	
	@Bean //오브젝트 생성을 담당하는 IoC용 메소드라는 표시이다. 
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}
	
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}

}
