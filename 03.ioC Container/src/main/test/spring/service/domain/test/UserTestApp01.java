package spring.service.domain.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;

import spring.service.domain.User;

/*
 * FileName : DiceTestAppUseSpring.java
 * ㅇ Hello Bean 사용하는 Application
 * ㅇ Hello Bean 과  dependency Relation
 * ㅇ Spring Framework 에서 제공하는 API를 사용 instance 생성
 * 		[[[ HelloTestApp02와 비교 ]]]
 * 		- hello.properties  ==> hello.xml
 * 		- HelloFactory ==> org.springframework.beans.factory.BeanFactory     
 
	==>	Spring Framework 은 다양한 메타데이터 중  xml 을 일반적으로 사용하며,
	  		instance 생성, 초기화, 객체의 상호 관계를  xml 에 선언적으로 기술. 
	  				
			<bean id="hello" class="spring.services.hello.Hello"/>
			
	==> 	HelloFactory :  Hello Data type 만 관리 하는 Factory 
	==> Spring Framework :모든 Data Type(Bean/POJO) 생성관리하는 Factory
 */
public class UserTestApp01 {

	/// main method
	public static void main(String[] args) {
		
		//1. BeanFactory 을 이용 xml 에 선언적으로 기술된 instance 정보 획득
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