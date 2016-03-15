package com.test.unit_01_1;

import java.sql.SQLException;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class UserDaoConnectionCountingTest {
	public static void main(String[] args) throws ClassNotFoundException,
	SQLException{
		
		//�������� �˻� (dependency lookup) 
		//:�ڽ��� �ʿ�� �ϴ� ���� ������Ʈ�� �ɵ������� ã�´�. 
		AnnotationConfigApplicationContext context=
				new AnnotationConfigApplicationContext(CountingDaoFactory.class);
		UserDao dao= context.getBean("userDao",UserDao.class);
		
		//DAO ��� �ڵ�
		CountingConnectionMaker ccm = context.getBean("connectionMaker",
				CountingConnectionMaker.class);
		//DL(�������� �˻�)�� ����ϸ� �̸��� �̿��� � ���̵� ������ �� �ִ�. 
		System.out.println("Connection counter: "+ccm.getCounter());
	}

}
