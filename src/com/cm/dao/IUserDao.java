package com.cm.dao;

import java.util.List;

import com.cm.domain.User;

public interface IUserDao {
	
	//通过id查找
	User findUserById(int userId);
	//通过用户名查找
	User findUserByName(String userName);
	//查找所有
	List<User> findAllUsers();
	//保存用户
	void saveUser(User user);
	//删除用户
	void deleteUser(User user);
	//检查用户是否管理员
	boolean isAds(int userId) ;
	//更新用户
	void updateUser(User user);
	//通过id删除用户
	void deleteById(int userId);
	//通过用户名判断用户是否存在
	boolean isUserExist(String userName);
}
