package com.test.unit_01_1;

import java.sql.SQLException;

public class main_test {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		UserDao dao= new UserDao();
		System.out.println("����");
		User user = new User();
		user.setId("kiela929");
		user.setName("������");
		user.setPassword("happy");
		
		dao.add(user);
		
		System.out.println(user.getId()+"��ϼ���");
		User user2 = dao.get(user.getId());
		System.out.println(user2.getName());
		
		System.out.println(user2.getPassword());
		
		System.out.println(user2.getId()+"��ȸ ����");
	}

}
