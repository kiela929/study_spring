package com.test.unit_01_1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration //���ø����̼� ���ؽ�Ʈ �Ǵ� �� ���丮�� ����� ����������� ǥ���̴�. 
public class DaoFactory {
	
	@Bean //������Ʈ ������ ����ϴ� IoC�� �޼ҵ��� ǥ���̴�. 
	public UserDao userDao(){
		return new UserDao(connectionMaker());
	}
	
	
	@Bean
	public ConnectionMaker connectionMaker(){
		return new DConnectionMaker();
	}

}
