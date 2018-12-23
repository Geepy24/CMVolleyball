package com.cm.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IResourceDao;
import com.cm.domain.Resource;
@Repository("resourceDao")
public class ResourceDaoImpl implements IResourceDao {

	@Autowired
	private HibernateTemplate hibernateTemplate;
	
	//保存
	@Override
	public void saveResource(Resource resource) {
		hibernateTemplate.save(resource) ;
		
	}
	//分页查找
	@Override
	public List<Resource> findAllResource(String tag, Integer currentPage, Integer maxResults) {
		System.out.println(tag+""+currentPage+""+maxResults);
		Resource resource = new Resource();
		resource.setResTag(tag);
		List<Resource> list =  hibernateTemplate.findByExample(resource, (currentPage-1)*maxResults, maxResults) ;
		System.out.println("list:"+list.get(0));
		return list ;
	}

	@Override
	public Long AllResourceNumber() {
		
		return (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Resource").get(0);
	}

	@Override
	public void deleteResource(Integer resId) {
		Resource resource = this.findResourceById(resId) ;	
		hibernateTemplate.delete(resource);
		
	}

	@Override
	public Resource findResourceById(Integer resId) {
		return (Resource) hibernateTemplate.find("FROM Resource where resId=?", resId).get(0);
	}

	@Override
	public List<Resource> findAllResource(String tag, Integer userId, Integer currentPage, Integer maxResults) {
		Resource resource = new Resource() ;
		resource.setResTag(tag); 
		resource.setUserId(userId);
		return hibernateTemplate.findByExample(resource, (currentPage-1)*maxResults, maxResults) ;
	}

	@Override
	public Long AllResourceNumber(Integer userId) {
		return (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Resource where userId=?",userId).get(0);
	}
	@Override
	public Long AllResourceNumber(Integer userId, String tag) {
		String hql = "SELECT COUNT(*) FROM Resource where userId=? and resTag=?" ;
		return (Long) hibernateTemplate.find(hql,new Object[] {userId,tag}).get(0);
	}
	@Override
	public Long AllResourceNumber(String tag) {
		Long i =  (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Resource where resTag=?",tag).get(0);
		System.out.println("数目"+i);
		return i ;
		
	}

}
