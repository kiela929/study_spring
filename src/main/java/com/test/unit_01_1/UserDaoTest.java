package com.test.unit_01_1;

import java.sql.SQLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{

		//		ApplicationContext context =
		//				new GenericXmlApplicationContext("com/test/unit_01_1/applicationContext.xml");
		//applicationContext에서 DaoFactory 대신 xml을 이용하여 빈을 관리하는 방법이다. 

		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);


		UserDao dao= context.getBean("userDao",UserDao.class);

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
