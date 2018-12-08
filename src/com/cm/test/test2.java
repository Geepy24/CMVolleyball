package com.cm.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cm.domain.User;
import com.cm.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:applicationContext.xml"})
public class test2 {
	
	@Autowired
	private IUserService userService ; 
	@Test
	public void testFindById() {
		User user = userService.findUserById(1); 
		System.out.println(user); 
		//ClassCaseException,hibernateTemplate��find()�������ص���һ��arrayList
		//Ҫget(0)
		
	}
	@Test
	public void testFindAll() {
		List<User> users = userService.findAllUsers() ;
		for(Object object : users) {
			System.out.println(object);
		}
		//�ɹ�
	}
	@Test
	public void testFindByName() {
		User user = userService.findUserByName("Geepy") ;
		System.out.println(user);
		//������ѯ��Ҫʹ��query����
	}
	@Test
	public void testSave() {
		User user = new User("test1","test1","test1","m") ;
		userService.saveUser(user);
		
	}
	@Test
	public void testDelete() {
	
		
		
	}
	
}
