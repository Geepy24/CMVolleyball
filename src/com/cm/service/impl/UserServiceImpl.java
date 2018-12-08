package com.cm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cm.dao.IUserDao;
import com.cm.domain.User;
import com.cm.service.IUserService;
@Service("userService")
public class UserServiceImpl implements IUserService{
	@Resource(name="userDao")
	private IUserDao userDao  ;
	
/*	
	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}
*/
	
	//通过id查找
	@Override
	public User findUserById(int userId) {
		return userDao.findUserById(userId);
	}
	//通过用户名查找
	@Override
	public User findUserByName(String userName) {
		return userDao.findUserByName(userName);
	}
	//查找所有
	@Override
	public List<User> findAllUsers() {
		return userDao.findAllUsers();
	}
	//保存
	@Override
	public void saveUser(User user) {
		 userDao.saveUser(user) ;
		
	}
	//删除
	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user) ;
	}
	//是否管理员
	@Override
	public boolean isAds(int userId) {
		return userDao.isAds(userId) ;
		
	}
	//更新用户信息
	@Override
	public void updateUser(User user) {
		
		userDao.updateUser(user) ;
		
	}
	//通过id删除
	@Override
	public void deleteById(int userId) {
		
		userDao.deleteById(userId) ;
		
	}
	//通过用户名判断用户是否存在
	@Override
	public boolean isUserExist(String userName) {
		
		return userDao.isUserExist(userName) ;
	}

	
	
	
	
}
