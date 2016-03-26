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
		//IoC�����̳� ����. ������ ���ÿ� �����̳ʷ� �����Ѵ�.
		ac.registerBeanDefinition("printer", new RootBeanDefinition(StringPrinter.class));
	
		BeanDefinition helloDef= new RootBeanDefinition(Hello.class);
		//�� ��Ÿ������ ���� ������Ʈ�� �����. 
		//�� Ŭ������ Hello�� �����Ѵ�. �̰��� �ǹ̴� <bean class="part2.unit_01.dao.Hello"/>�� �ش��ϴ� ��Ÿ������.
		
		helloDef.getPropertyValues().addPropertyValue("name","Spring");
		//���� name������Ƽ�� �� ���� �����Ѵ�. <property name="name" value="Spring"/>�� �ش��Ѵ�.
		helloDef.getPropertyValues().addPropertyValue("printer",new RuntimeBeanReference("printer"));
		
		
		ac.registerBeanDefinition("hello", helloDef);
		//helloDef�� hello��� �̸����� ��Ͻ�Ų��. <bean id="hello".../>�� �ش�.
		
		Hello hello = ac.getBean("hello",Hello.class);
		//��ϵ� ���� hello�� ��ü hello�� �޾ƿ´�.
		hello.print();
		
		assertThat(ac.getBean("printer").toString(), is("Hello Spring"));
	
	}

}
