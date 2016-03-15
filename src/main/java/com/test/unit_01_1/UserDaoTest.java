package com.test.unit_01_1;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		ApplicationContext context =
				new GenericXmlApplicationContext("com/test/unit_01_1/applicationContext.xml");
		//applicationContext에서 DaoFactory 대신 xml을 이용하여 빈을 관리하는 방법이다. 
		

		UserDao dao= context.getBean("userDao",UserDao.class);
		UserDao dao1= context.getBean("userDao",UserDao.class);
		UserDao dao2= context.getBean("userDao",UserDao.class);

		System.out.println("dao1과 dao2는 같은가? "+(dao1==dao2));
		System.out.println("dao1: "+dao1);
		System.out.println("dao2: "+dao2);
	/*
	 * getBean() 메소드는 ApplicationContext가 관리하는 오브젝트를 요청하는 메소드다. 
	 * 즉, "userDao"는 DaoFactory가 가지고 있는 Bean정보 중 userDao라는 이름을 가진 빈을 가지고 오라는 것이다. 
	 */


		User user= new User();
		user.setId("phj929");
		user.setName("Han");
		user.setPassword("enjoy");
		dao.add(user);

		System.out.println(user.getId()+"등록성공");
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());

		System.out.println(user2.getPassword());

		System.out.println(user2.getId()+"조회 성공");


		/**
		 * 결과 화면
		 * 
		 * D사의 connection입니다.
		 *	phj929등록성공
		 *	D사의 connection입니다.
		 *	Han
		 *	enjoy
		 *	phj929조회 성공
		 */

	}

}
