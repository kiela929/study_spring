package com.test.unit_01_1;

import java.sql.SQLException;

public class UserDaoTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		
		ConnectionMaker connectionMaker = new DConnectionMaker();
		//UserDao�� ����� ConnectionMaker ���� Ŭ������ �����ϰ� ������Ʈ�� �����. 
		//��, ���� ����� ����� � connection�� ���� �����ϸ� �Ǵ°�! �̰��� ����� �� ���������̴�. 
		//���� ����ڰ� �ٲ���� �� ���� �̿��� �ڵ带 �������� �ʾƵ� �ȴ�
		//���: � ȸ���� connection�� ������ �������� main�� ����ϴ� ��!! 
		
		UserDao dao= new UserDao(connectionMaker);
		/*
		 * 1. userdao ���� -> ����� D���� connection�� ����Ϸ��� �� 
		 * 2. ����� connectionMaker Ÿ���� ������Ʈ ����.
		 * �ᱹ �� ������Ʈ ������ �������� ���� ȿ��
		 */

	}

}
