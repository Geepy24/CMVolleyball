package com.cm.service;

import java.util.List;

import com.cm.domain.User;


public interface IUserService {

	/**
	 * 通过id查找一个用户
	 * @return
	 */
	User findUserById(int userId) ;
	/**
	 *	通过名字查找用户
	 */
	User findUserByName(String userName) ;
	/**
	 * 	根据用户名判断用户是否存在
	 */
	boolean isUserExist(String userName) ;
	/**
	 * 	根据用户id检查一个用户是否是管理员
	 */
	boolean isAds(int userId) ;
	/**
	 * 	查找所有用户
	 * @return
	 */
	List<User> findAllUsers() ;
	
	/**
	 * 	保存用户
	 */
	void saveUser(User user) ;
	
	/**
	 * 删除用户
	 */
	void deleteUser(User user) ;
	/**
	 * 	更新用户
	 * @param user
	 */
	void updateUser(User user);
	
	/**
	 * 通过id删除
	 * @param i 
	 */
	void deleteById(int userId);
}
