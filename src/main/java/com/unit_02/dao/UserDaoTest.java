package com.unit_02.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.unit_02.domain.User;



public class UserDaoTest {
	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{
		
		ApplicationContext context = new GenericXmlApplicationContext("com/unit_02/dao/applicationContext.xml");
		
		UserDao dao= context.getBean("userDao",UserDao.class); 
		User user1 = new User("moon","��޺�","moonlight");
		User user2 = new User("sunny","���޺�","sunlight");
		
		
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.add(user1);
		dao.add(user2);
		assertThat(dao.getCount(), is(2));

		User userget1 = dao.get(user1.getId());
		assertThat(userget1.getName(), is(user1.getName()));
		assertThat(userget1.getPassword(), is(user1.getPassword()));
		//ù��° User�� id�� get()�� �����ϸ� ù��° user�� ���� ���� ������Ʈ�� �����ִ��� Ȯ���Ѵ�. 
		
		
		User userget2 =dao.get(user2.getId());
		assertThat(userget2.getName(),is(user2.getName()));
		assertThat(userget2.getPassword(),is(user2.getPassword()));
		//�ι�° user�� ���ؼ��� ���� ������� �����Ѵ�.
		
		
		
		
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
	
	
	/*
	 * ���ܻ�Ȳ�� ���� �׽�Ʈ. 
	 * �߿��� ���� ���ܰ� �߻��ؼ� ������ ���߸� �׽�Ʈ�� �����ϴ� ��!!
	 * expected�� �߻��Ұ��̶�� ����Ǵ� ����Ŭ������ �־��ָ� �ȴ�. 
	 */
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		ApplicationContext context = 
				new GenericXmlApplicationContext("com/unit_02/dao/applicationContext.xml");
		
		UserDao dao=context.getBean("userDao",UserDao.class);
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));
		
		dao.get("unkown_id");
		//�� �޼ҵ� ���� �߿� ���ܰ� �߻��ؾ��Ѵ�. 
		//������ ������ ���Ǵ� ���ܰ� �߻����� ������ �׽�Ʈ�� �����Ѵ�. 
		
	}
	
	public static void main(String[] args) {
		JUnitCore.main("com.unit_02.dao.UserDaoTest");
		//main���� junit test�� �������ش�. 
		//java application ���� �����Ű�� �ܼ�â���� ��� Ȯ���� ����.
		//Junit���� �����Ű�� main�� JUnitCore �ڵ�� �ʿ����. IDE �������� Ȯ�� ����. 
	}

	

}
