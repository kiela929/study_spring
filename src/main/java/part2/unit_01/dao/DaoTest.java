package part2.unit_01.dao;


import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.support.StaticApplicationContext;

public class DaoTest {
	
	@Test
	public void registerBeanWithDependency(){
		StaticApplicationContext ac= new StaticApplicationContext();
		//IoC컨테이너 생성. 생성과 동시에 컨테이너로 동작한다.
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
	
		BeanDefinition helloDef= new RootBeanDefinition(Hello.class);
		//빈 메타정보를 담은 오브젝트를 만든다. 
		//빈 클래스는 Hello로 지정한다. 이것의 의미는 <bean class="part2.unit_01.dao.Hello"/>에 해당하는 메타정보다.
		
		helloDef.getPropertyValues().addPropertyValue("name","Spring");
		//빈의 name프로퍼티에 들어갈 값을 지정한다. <property name="name" value="Spring"/>에 해당한다.
		helloDef.getPropertyValues().addPropertyValue("printer",new RuntimeBeanReference("printer"));
		
		
		ac.registerBeanDefinition("hello", helloDef);
		//helloDef를 hello라는 이름으로 등록시킨다. <bean id="hello".../>에 해당.
		
		Hello hello = ac.getBean("hello",Hello.class);
		//등록된 빈인 hello를 객체 hello에 받아온다.
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	
	}

}
