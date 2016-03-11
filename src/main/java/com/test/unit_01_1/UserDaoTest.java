package com.test.unit_01_1;

import java.sql.SQLException;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{


		UserDao dao= new DaoFactory().userDao();
		/**
		 * 이전에는 이 곳에서 어느 회사의 connection을 사용할지 결정 후 오브젝트 생성, 테스트 까지 함께 진행하였지만,
		 * Factory클래스를 따로 만들어서 오브젝트 생성하는 것을 분리하였다. 
		 * 그러므로 현재는 실행하는 코드만 존재하는 것이다. 
		 * 이것은 기능의 분리가 일어난 것이다. 
		 * 
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
