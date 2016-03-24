package com.unit_02.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.unit_02.domain.User;



public class UserDaoTest {
	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{
		
		ApplicationContext context = new GenericXmlApplicationContext("com/unit_02/dao/applicationContext.xml");
		
		UserDao dao= context.getBean("userDao",UserDao.class); 
		User user = new User("moon","��޺�","moonlight");
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user);
		assertThat(dao.getCount(), is(1));

		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
		
		
		
	}
	
	@Test
	public void count()throws SQLException, ClassNotFoundException{
		ApplicationContext context = 
				new GenericXmlApplicationContext("com/unit_02/dao/applicationContext.xml");
		
		UserDao dao= context.getBean("userDao",UserDao.class);
		User user1 = new User("gyumee","�ڼ�ö","springno1");
		User user2 = new User("leegw700","�̱��","springno2");
		User user3 = new User("bumjin","�ڹ���","springno3");
		
		dao.deleteAll();
		assertThat(dao.getCount(),is(0));
		
		dao.add(user1);
		assertThat(dao.getCount(),is(1));
		
		dao.add(user2);
		assertThat(dao.getCount(), is(2));
		
		dao.add(user3);
		assertThat(dao.getCount(), is(3));
		
	}
	
	public static void main(String[] args) {
		JUnitCore.main("com.unit_02.dao.UserDaoTest");
		//main���� junit test�� �������ش�. 
		//java application ���� �����Ű�� �ܼ�â���� ��� Ȯ���� ����.
		//Junit���� �����Ű�� main�� JUnitCore �ڵ�� �ʿ����. IDE �������� Ȯ�� ����. 
	}

	

}
