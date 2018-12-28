package com.cm.service;

import java.util.List;

import com.cm.domain.Resource;

/**
 * 	资源类的业务层接口
 * @author Huangjiping
 *
 */
public interface IResourceService {

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
	Integer nextResourceId(Integer resId) ;
	/**
	 * 通过id查找上一个条记录的id
	 */
	Integer preResourceId(Integer resId) ;
}
