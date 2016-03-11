package com.test.unit_01_1;

public class DaoFactory {
	
	public UserDao userDao(){
		
		ConnectionMaker connectionMaker= new DConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		/**
		 * � ȸ���� connection�� ������� �����ϴ� �κ��̴�. ->����� D���� connection�� ���! 
		 * ������ �ڵ忡���� main���� ��� ȸ���� Ŀ�ؼ��� ������� ���ؼ� ������Ʈ�� ����� �ϰ� �����ϴ� ���� �Բ� �����ߴ�. 
		 * �̰��� �и��ϱ� ���ؼ� ������ ���� ������̴�. 
		 * 
		 * ���� �� Ŭ������ � ȸ���� connection�� ���� �����ϴ� ���� ����ϴ� Ŭ�����̴�.
		 * �̷� Ŭ������ ���丮(factory)��� �Ѵ�. 
		 * 
		 *  �̰��� ������ ���Ͽ��� ���ϴ� Ư���� ������ �ذ��ϱ� ���� ���Ǵ� �߻����丮 �����̳� ���丮�޼ҵ� ���ϰ��� �ٸ��� ȥ������ ����.
		 */
		
		return userDao;
	}

}
