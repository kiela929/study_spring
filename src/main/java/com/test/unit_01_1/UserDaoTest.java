package com.test.unit_01_1;

import java.sql.SQLException;

public class UserDaoTest {

	public static void main(String[] args) throws ClassNotFoundException, SQLException{


		UserDao dao= new DaoFactory().userDao();
		/**
		 * �������� �� ������ ��� ȸ���� connection�� ������� ���� �� ������Ʈ ����, �׽�Ʈ ���� �Բ� �����Ͽ�����,
		 * FactoryŬ������ ���� ���� ������Ʈ �����ϴ� ���� �и��Ͽ���. 
		 * �׷��Ƿ� ����� �����ϴ� �ڵ常 �����ϴ� ���̴�. 
		 * �̰��� ����� �и��� �Ͼ ���̴�. 
		 * 
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
