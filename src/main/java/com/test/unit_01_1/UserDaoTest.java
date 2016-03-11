package com.test.unit_01_1;

import java.sql.SQLException;

public class UserDaoTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		
		
		ConnectionMaker connectionMaker = new DConnectionMaker();
		//UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트를 만든다. 
		//즉, 실제 사용할 사람이 어떤 connection을 쓸지 결정하면 되는것! 이것이 제대로 된 의존관계이다. 
		//이제 사용자가 바뀌었을 때 메인 이외의 코드를 수정하지 않아도 된다
		//결론: 어떤 회사의 connection을 쓸지의 결정권은 main이 담당하는 것!! 
		
		UserDao dao= new UserDao(connectionMaker);
		/*
		 * 1. userdao 생성 -> 현재는 D사의 connection을 사용하려고 함 
		 * 2. 사용할 connectionMaker 타입의 오브젝트 제공.
		 * 결국 두 오브젝트 사이의 의존관계 설정 효과
		 */

	}

}
