package com.cm.test;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cm.service.IUserService;
import com.cm.service.impl.UserServiceImpl;
/**
 * 	����Spring��DI��hibernate�Ķ���ʹ��
 * @author Huangjiping
 *
 */
public class test1 {

	
	
	public static void main(String[] args) {
		
//		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml") ;
//		IUserService userService= (IUserService)ac.getBean("userService") ;
//		
//		userService.saveUser();
		long t = System.currentTimeMillis();//��õ�ǰʱ��ĺ�����
        Random rd = new Random(t);//��Ϊ���������뵽Random�Ĺ�������
        System.out.println(rd.nextInt(1000)+1);//�����������
		
		
	}
	
	
}
