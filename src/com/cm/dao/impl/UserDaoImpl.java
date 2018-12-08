package com.cm.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IUserDao;
import com.cm.domain.User;
@Repository("userDao")
public class UserDaoImpl implements IUserDao {

	/**
	 * 	注意，如果语句执行查找没有找到结果，执行query.list().get(0)会报错
	 */
	@Resource(name="hibernateTemplate")
	private HibernateTemplate hibernateTemplate ;
	
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	@Override
	public User findUserById(int userId) {
		
		User user = null ;
		try {
			user = (User) hibernateTemplate.find("from User where userId="+userId).get(0);
		}
		catch(Exception e){
			return user;
		}
		return user;
		
	}

	@Override
	public User findUserByName(String userName) {
			User user = null ;
			try {
				String hql = "from User where userName=?" ;
				Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
				query.setParameter(0, userName) ;
				user =  (User) query.list().get(0) ;
			}catch (RuntimeException e) {
				return null ;
			}
			return user ;
			
		
	}

	@Override
	public List<User> findAllUsers() {
		return (List<User>) hibernateTemplate.find("from User");
	}

	@Override
	public void saveUser(User user) {
		//System.out.println("DI success");
		
		
//		User user = new User("Lam","袁傲蓝","123","女") ;
//		//加载hibernate主配置文件
//		Configuration cfg = new org.hibernate.cfg.Configuration() ;
//		cfg.configure() ;
//		//创建工厂
//		SessionFactory sessionFactory = cfg.buildSessionFactory() ;
//		//得到操作对象session
//		Session session = sessionFactory.openSession();
//		
//		//开启事务
//		Transaction tx = session.beginTransaction() ;
//		session.save(user) ;
//		System.out.println("save success");
//		tx.commit();
//		sessionFactory.close();
	
		hibernateTemplate.save(user) ;
		
	}

	@Override
	public void deleteUser(User user) {
		hibernateTemplate.delete(user);
		System.out.println("删除成功");
	}
	
	//检查用户是否管理员
	@Override
	public boolean isAds(int userId) {
		
		String hql = "from Ads where userId=?";
		System.out.println("执行isAds");
		try {			
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userId) ;
			
		
		}catch (Exception e) {
			e.printStackTrace();
			return false ;
		}
		
		return true;
	}

	//更新用户
	@Override
	public void updateUser(User user) {
		//会自动根据主键来查找后更新，不用先查找
		hibernateTemplate.update(user);
		
	}

	
	/**
	 * 	通过id删除用户
	 */
	@Override
	public void deleteById(int userId) {
		String hql = "delete from Ads where userId=?" ;
		try {
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userId) ;
			System.out.println("删除成功");
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("删除失败");
		}
	
	}
/**
 * 通过用户名判断用户是否存在
 */
	@Override
	public boolean isUserExist(String userName) {
		 String hql = "select userId from User where userName=?" ;
		 //hibernata 的query到底怎么执行？
		try {
			Query query = hibernateTemplate.getSessionFactory().getCurrentSession().createQuery(hql) ;
			query.setParameter(0, userName) ;
			
			System.out.println(query.list().get(0)) ;
			System.out.println("没出错");
		}catch(Exception e){
			System.out.println("没查到，catch");
			return false ;
		}
		
		return true ;	
	}

}
