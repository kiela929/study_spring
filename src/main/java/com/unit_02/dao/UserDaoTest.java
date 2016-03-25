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
//스프링의 테스트 컨텍스트 프레임워크의 JUnit 확장기능 지정함!
@ContextConfiguration(locations="/com/unit_02/dao/applicationContext.xml")
//테스트 컨텍스트가 어떤 applicationContext를 만들지 그 위치지정하기

public class UserDaoTest {
	
	@Autowired 
	private UserDao dao; 
	/*
	 * @Autowired는 스프링의 DI에 사용되는 애노테이션이다.
	 * 이게 붙은 인스턴스변수가 있으면, 테스트 컨텍스트 프레임워크는
	 * 변수타입과 일치하는 컨텍스트 내의 빈을 찾는다. 
	 * 타입이 일치하는 빈이 있으면 인스턴스 변수에 주입해준다. 
	 * 일반적으로 주입을 위해서는 생성자,수정자 같은 메소드가 있어야했지만
	 * 이경우는 메소드 없어도 주입 가능하다!!
	 * 별도의 DI설정도 필요없다. 타입정보에 의해서 자동으로 빈을 가지고오는것!
	 * 이런 방법을 타입에 의한 자동와이어링이라고 한다.   
	 */


	private User user1;
	private User user2;
	private User user3;
	/*
	 * setUp() 메소드에서 만드는 오브젝트를
	 * 테스트 메소드에서 사용할 수 있도록 인스턴스 변수로 선언한다. 
	 */


	//@Test가 실행되기 전에 실행되는 메소드를 정의하자.
	@Before
	public void setUp(){

//		this.dao= this.context.getBean("userDao",UserDao.class); 
		
		this.user1 = new User("moon","김달빛","moonlight");
		this.user2 = new User("sunny","이햇빛","sunlight");
		this.user3 = new User("bumjin","박범진","springno3");
		

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
		//첫번째 User의 id로 get()을 실행하면 첫번째 user의 값을 가진 오브젝트를 돌려주는지 확인한다. 


		User userget2 =dao.get(user2.getId());
		assertThat(userget2.getName(),is(user2.getName()));
		assertThat(userget2.getPassword(),is(user2.getPassword()));
		//두번째 user에 대해서도 같은 방식으로 진행한다.




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
	 * 예외상황에 대한 테스트. 
	 * 중요한 점은 예외가 발생해서 오류가 나야만 테스트는 성공하는 것!!
	 * expected에 발생할것이라고 예상되는 예외클래스를 넣어주면 된다. 
	 */
	@Test(expected=EmptyResultDataAccessException.class)
	public void getUserFailure() throws SQLException, ClassNotFoundException{
		dao.deleteAll();
		assertThat(dao.getCount(), is(0));

		dao.get("unkown_id");
		//이 메소드 실행 중에 예외가 발생해야한다. 
		//위에서 적어준 기대되는 예외가 발생하지 않으면 테스트가 실패한다. 
	}
	public static void main(String[] args) {
		JUnitCore.main("com.unit_02.dao.UserDaoTest");
		//main에서 junit test를 진행해준다. 
		//java application 으로 실행시키면 콘솔창에서 결과 확인이 가능.
		//Junit으로 실행시키면 main의 JUnitCore 코드는 필요없다. IDE 제공으로 확인 가능. 
	}
}
