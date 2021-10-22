package spring.service.lifecycle;

import org.springframework.beans.factory.BeanNameAware;

public class LifeCycle01 implements BeanNameAware{

	public LifeCycle01()  {
		// TODO Auto-generated constructor stub
		System.out.println("\n"+getClass().getName()+"����Ʈ");
		
	}
	public void setBeanName(String name) {
		System.out.println("::"+getClass().getName()+"setBeanName s");
		System.out.println("xml �� ���ǵ� beanName :"+name);
		System.out.println("::"+getClass().getName()+"setBeanName e");
	}
	public void init() {
		System.out.println("::"+getClass().getName()+"init");
		
	}
	public void destroy() {
		System.out.println("::"+getClass().getName()+"destroy");
	}

}
