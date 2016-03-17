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
		
		User user= new User();
		user.setId("moon");
		user.setName("��޺�");
		user.setPassword("moonlight");
		
		dao.add(user);

		User user2 = dao.get(user.getId());
		
		assertThat(user2.getName(),is(user.getName()));
		assertThat(user2.getPassword(),is(user.getPassword()));
		
	}
	
	public static void main(String[] args) {
		JUnitCore.main("com.unit_02.dao.UserDaoTest");
		//main���� junit test�� �������ش�. 
	}

	

}
