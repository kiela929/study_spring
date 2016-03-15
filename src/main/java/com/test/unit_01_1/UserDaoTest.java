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
		//applicationContext���� DaoFactory ��� xml�� �̿��Ͽ� ���� �����ϴ� ����̴�. 
		

		UserDao dao= context.getBean("userDao",UserDao.class);
		UserDao dao1= context.getBean("userDao",UserDao.class);
		UserDao dao2= context.getBean("userDao",UserDao.class);

		System.out.println("dao1�� dao2�� ������? "+(dao1==dao2));
		System.out.println("dao1: "+dao1);
		System.out.println("dao2: "+dao2);
	/*
	 * getBean() �޼ҵ�� ApplicationContext�� �����ϴ� ������Ʈ�� ��û�ϴ� �޼ҵ��. 
	 * ��, "userDao"�� DaoFactory�� ������ �ִ� Bean���� �� userDao��� �̸��� ���� ���� ������ ����� ���̴�. 
	 */


		User user= new User();
		user.setId("phj929");
		user.setName("Han");
		user.setPassword("enjoy");
		dao.add(user);

		System.out.println(user.getId()+"��ϼ���");
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());

		System.out.println(user2.getPassword());

		System.out.println(user2.getId()+"��ȸ ����");


		/**
		 * ��� ȭ��
		 * 
		 * D���� connection�Դϴ�.
		 *	phj929��ϼ���
		 *	D���� connection�Դϴ�.
		 *	Han
		 *	enjoy
		 *	phj929��ȸ ����
		 */

	}

}
