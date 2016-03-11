package com.test.unit_01_1;

public class DaoFactory {
	
	public UserDao userDao(){
		
		ConnectionMaker connectionMaker= new DConnectionMaker();
		UserDao userDao = new UserDao(connectionMaker);
		/**
		 * 어떤 회사의 connection을 사용할지 결정하는 부분이다. ->현재는 D사의 connection을 사용! 
		 * 이전의 코드에서는 main에서 어느 회사의 커넥션을 사용할지 정해서 오브젝트를 만드는 일과 실행하는 일을 함께 진행했다. 
		 * 이것을 분리하기 위해서 다음과 같이 만든것이다. 
		 * 
		 * 현재 이 클래스는 어떤 회사의 connection을 쓸지 결정하는 것을 담당하는 클래스이다.
		 * 이런 클래스를 팩토리(factory)라고 한다. 
		 * 
		 *  이것은 디자인 패턴에서 말하는 특별한 문제를 해결하기 위해 사용되는 추상팩토리 패턴이나 팩토리메소드 패턴과는 다르니 혼동하지 말자.
		 */
		
		return userDao;
	}

}
