package spring.service.domain.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import spring.service.domain.User;

/*
 * FileName : DiceTestAppUseSpring.java
 * �� Hello Bean ����ϴ� Application
 * �� Hello Bean ��  dependency Relation
 * �� Spring Framework ���� �����ϴ� API�� ��� instance ����
 * 		[[[ HelloTestApp02�� �� ]]]
 * 		- hello.properties  ==> hello.xml
 * 		- HelloFactory ==> org.springframework.beans.factory.BeanFactory     
 
	==>	Spring Framework �� �پ��� ��Ÿ������ ��  xml �� �Ϲ������� ����ϸ�,
	  		instance ����, �ʱ�ȭ, ��ü�� ��ȣ ���踦  xml �� ���������� ���. 
	  				
			<bean id="hello" class="spring.services.hello.Hello"/>
			
	==> 	HelloFactory :  Hello Data type �� ���� �ϴ� Factory 
	==> Spring Framework :��� Data Type(Bean/POJO) ���������ϴ� Factory
 */
public class UserTestApp01 {

	/// main method
	public static void main(String[] args) {
		
		//1. BeanFactory �� �̿� xml �� ���������� ����� instance ���� ȹ��
		BeanFactory factory =
				new XmlBeanFactory( new FileSystemResource
																	("./src/main/resources/config/userservice01.xml") );

		//ApplicationContext factory = new ClassPathXmlApplicationContext("/config/userservice01.xml");
		
		System.out.println("====================================");
		User user01 = (User)factory.getBean("user01");
		System.out.println(user01);

		System.out.println("====================================");
		User user02 = (User)factory.getBean("user02");
		System.out.println(user02);

		System.out.println("====================================");
		User user03 = (User)factory.getBean("user03");
		System.out.println(user03);

		System.out.println("====================================");
		User user04 = (User)factory.getBean("user04");
		System.out.println(user04);

		System.out.println("====================================");
		User user05 = (User)factory.getBean("user05");
		System.out.println(user05);
	}
	
}//end of class