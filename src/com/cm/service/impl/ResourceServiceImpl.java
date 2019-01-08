package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dao.IResourceDao;
import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.domain.User;
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
	public Long AllResourceNumber(User	 user) {
		
		return resourceDao.AllResourceNumber(user);
	}

	@Override
	public Long AllResourceNumber(User user, String tag) {
		return resourceDao.AllResourceNumber(user, tag);
	}

	@Override
	public Long AllResourceNumber(String tag) {
		return resourceDao.AllResourceNumber(tag);
	}

	@Override
	public Integer nextResourceId(Integer resId,String tag) {
		return resourceDao.nextResourceId(resId,tag);
	}

	@Override
	public Integer preResourceId(Integer resId,String tag) {
		return resourceDao.preResourceId(resId,tag);
	}
//-----------------------------´ýÉóºËÊÓÆµ£¬Í¼Æ¬²Ù×÷-----------------------
	@Override
	public void savePictureCheck(PictureCheck pictureCheck) {
		resourceDao.savePictureCheck(pictureCheck);
		
	}

	@Override
	public void saveMovieCheck(MovieCheck movieCheck) {

		resourceDao.saveMovieCheck(movieCheck);
	}

	@Override
	public PictureCheck findPCById(Integer picId) {

		return resourceDao.findPCById(picId);
	}

	@Override
	public MovieCheck findMCById(Integer movId) {

		return resourceDao.findMCById(movId);
	}

	@Override
	public List<PictureCheck> findPCsByCheckTag(Integer checkTag, Integer currentPage, Integer maxResults) {

		return resourceDao.findPCsByCheckTag(checkTag, currentPage, maxResults);
	}

	@Override
	public List<MovieCheck> findMCsByCheckTag(Integer checkTag, Integer currentPage, Integer maxResults) {

		return resourceDao.findMCsByCheckTag(checkTag, currentPage, maxResults);
	}

	@Override
	public List<PictureCheck> findPCsByUserId(Integer userId, Integer currentPage, Integer maxResults) {

		return resourceDao.findPCsByUserId(userId, currentPage, maxResults);
	}

	@Override
	public List<MovieCheck> findMCsByUserId(Integer userId, Integer currentPage, Integer maxResults) {

		return resourceDao.findMCsByUserId(userId, currentPage, maxResults);
	}

	@Override
	public void updatePictureCheck(PictureCheck pictureCheck) {

		resourceDao.updatePictureCheck(pictureCheck);
	}

	@Override
	public void updateMovieCheck(MovieCheck movieCheck) {

		resourceDao.updateMovieCheck(movieCheck);
	}

	@Override
	public List<PictureCheck> findPCsByTagAndUserId(Integer checkTag, Integer userId, Integer currentPage,
			Integer maxResults) {

		return resourceDao.findPCsByTagAndUserId(checkTag, userId, currentPage, maxResults);
	}

	@Override
	public List<MovieCheck> findMCsByTagAndUserId(Integer checkTag, Integer userId, Integer currentPage,
			Integer maxResults) {

		return resourceDao.findMCsByTagAndUserId(checkTag, userId, currentPage, maxResults);
	}

	@Override
	public Long findAllMcs() {
		return resourceDao.findAllMcs();
	}

	@Override
	public Long findAllPcs() {
		return resourceDao.findAllPcs();
	}

	@Override
	public Long findAllMcsByUserId(Integer userId) {
		return resourceDao.findAllMcsByUserId(userId);
	}

	@Override
	public Long findAllPcsByUserId(Integer userId) {
		return resourceDao.findAllPcsByUserId(userId);
	}

	
	
	
	
	
}
