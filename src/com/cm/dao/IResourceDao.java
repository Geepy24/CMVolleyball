package com.cm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cm.domain.Resource;
public interface IResourceDao {
	/**
	 * 	保存资源
	 */
	void saveResource(Resource resource) ;
	/**
	 * 	通过类别，时间倒序分页查找所有资源
	 */
	List<Resource> findAllResource(String tag , Integer currentPage,Integer maxResults) ;
	/**
	 * 	通过类别与用户id，时间倒序分页查找所有资源
	 */
	List<Resource> findAllResource(String tag , Integer userId ,Integer currentPage,Integer maxResults) ;
	
	/**
	 * 	通过用户id查找总数
	 */
	Long AllResourceNumber(Integer userId);
	/**
	 * 	通过id和分类查找资源总数
	 */
	Long AllResourceNumber(Integer userId , String tag);
	/**
	 * 	查找分类下的资源总数
	 */
	Long AllResourceNumber(String tag);
	/**
	 * 查找资源总数
	 */
	Long AllResourceNumber();
	/**
	 * 删除资源
	 */
	void deleteResource(Integer resId) ;
	/**
	 * 	通过id查找资源
	 */
	Resource findResourceById(Integer resId) ;
	/**
	 * 通过id查找下一条记录的id
	 */
	Integer nextResourceId(Integer resId,String tag) ;
	/**
	 * 通过id查找上一个条记录的id
	 */
	Integer preResourceId(Integer resId,String tag);
}
