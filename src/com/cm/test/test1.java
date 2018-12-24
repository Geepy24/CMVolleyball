package com.cm.test;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cm.service.IUserService;
import com.cm.service.impl.UserServiceImpl;
/**
 * 	测试Spring的DI和hibernate的独立使用
 * @author Huangjiping
 *
 */
public class test1 {

	
	
	public static void main(String[] args) {
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml") ;
//		IUserService userService= (IUserService)ac.getBean("userService") ;
//		
//		userService.saveUser();
		long t = System.currentTimeMillis();//获得当前时间的毫秒数
        Random rd = new Random(t);//作为种子数传入到Random的构造器中
        System.out.println(rd.nextInt(1000)+1);//生成随机整数
		
		
	}
	
	
}
