package com.unit_02.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.unit_02.domain.User;


@RunWith(SpringJUnit4ClassRunner.class) 
//�������� �׽�Ʈ ���ؽ�Ʈ �����ӿ�ũ�� JUnit Ȯ���� ������!
@ContextConfiguration(locations="/com/unit_02/dao/applicationContext.xml")
//�׽�Ʈ ���ؽ�Ʈ�� � applicationContext�� ������ �� ��ġ�����ϱ�

public class UserDaoTest {
	
	@Autowired 
	private UserDao dao; 
	/*
	 * @Autowired�� �������� DI�� ���Ǵ� �ֳ����̼��̴�.
	 * �̰� ���� �ν��Ͻ������� ������, �׽�Ʈ ���ؽ�Ʈ �����ӿ�ũ��
	 * ����Ÿ�԰� ��ġ�ϴ� ���ؽ�Ʈ ���� ���� ã�´�. 
	 * Ÿ���� ��ġ�ϴ� ���� ������ �ν��Ͻ� ������ �������ش�. 
	 * �Ϲ������� ������ ���ؼ��� ������,������ ���� �޼ҵ尡 �־��������
	 * �̰��� �޼ҵ� ��� ���� �����ϴ�!!
	 * ������ DI������ �ʿ����. Ÿ�������� ���ؼ� �ڵ����� ���� ��������°�!
	 * �̷� ����� Ÿ�Կ� ���� �ڵ����̾�̶�� �Ѵ�.   
	 */


	private User user1;
	private User user2;
	private User user3;
	/*
	 * setUp() �޼ҵ忡�� ����� ������Ʈ��
	 * �׽�Ʈ �޼ҵ忡�� ����� �� �ֵ��� �ν��Ͻ� ������ �����Ѵ�. 
	 */


	//@Test�� ����Ǳ� ���� ����Ǵ� �޼ҵ带 ��������.
	@Before
	public void setUp(){

//		this.dao= this.context.getBean("userDao",UserDao.class); 
		
		this.user1 = new User("moon","��޺�","moonlight");
		this.user2 = new User("sunny","���޺�","sunlight");
		this.user3 = new User("bumjin","�ڹ���","springno3");
		

	}
	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException{


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
