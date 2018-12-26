package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dao.IResourceDao;
import com.cm.domain.Resource;
import com.cm.service.IResourceService;
@Service("resourceService")
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private IResourceDao resourceDao ; 
	
	@Override
	public void saveResource(Resource resource) {
		resourceDao.saveResource(resource) ;
		
	}

	@Override
	public List<Resource> findAllResource(String tag, Integer currentPage, Integer maxResults) {

		return resourceDao.findAllResource(tag, currentPage, maxResults);
	}

	@Override
	public Long AllResourceNumber() {
		return resourceDao.AllResourceNumber();
	}

	@Override
	public void deleteResource(Integer resId) {
		resourceDao.deleteResource(resId);
		
	}

	@Override
	public Resource findResourceById(Integer resId) {
		return resourceDao.findResourceById(resId);
	}

	@Override
	public List<Resource> findAllResource(String tag, Integer userId, Integer currentPage, Integer maxResults) {
		
		return resourceDao.findAllResource(tag, userId, currentPage, maxResults);
	}

	@Override
	public Long AllResourceNumber(Integer userId) {
		
		return resourceDao.AllResourceNumber(userId);
	}

	@Override
	public Long AllResourceNumber(Integer userId, String tag) {
		return resourceDao.AllResourceNumber(userId, tag);
	}

	@Override
	public Long AllResourceNumber(String tag) {
		return resourceDao.AllResourceNumber(tag);
	}

	@Override
	public Integer nextResourceId(Integer resId) {
		return resourceDao.nextResourceId(resId);
	}

	@Override
	public Integer preResourceId(Integer resId) {
		return resourceDao.preResourceId(resId);
	}

	
	
	
	
	
}
