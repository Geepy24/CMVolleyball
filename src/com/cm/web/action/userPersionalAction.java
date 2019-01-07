package com.cm.web.action;

import com.cm.domain.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
/**
 *	ÔÝÎÞ
 * @author mac
 *
 */
public class userPersionalAction extends ActionSupport implements ModelDriven<User> {

	
	private User user ;
	@Override
	public User getModel() {

		return user;
	}
		
	
}
