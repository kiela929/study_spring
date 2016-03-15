package com.test.unit_01_1;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException,
	SQLException{
		
		//의존관계 검색 (dependency lookup) 
		//:자신이 필요로 하는 의존 오브젝트를 능동적으로 찾는다. 
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao= context.getBean("userDao",UserDao.class);
		
		//DAO 사용 코드
		CountingConnectionMaker ccm = context.getBean("connectionMaker",
				CountingConnectionMaker.class);
		//DL(의존관계 검색)을 사용하면 이름을 이용해 어떤 빈이든 가져올 수 있다. 
		System.out.println("Connection counter: "+ccm.getCounter());
	}

}
