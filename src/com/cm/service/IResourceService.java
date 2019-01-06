package com.cm.service;

import java.util.List;

import com.cm.domain.MovieCheck;
import com.cm.domain.PictureCheck;
import com.cm.domain.Resource;
import com.cm.utils.movieUtils;

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
	Integer nextResourceId(Integer resId,String tag) ;
	/**
	 * 通过id查找上一个条记录的id
	 */
	Integer preResourceId(Integer resId,String tag) ;
	//---------------------------------待审核视频，图片操作---------------------------
	//---------------------------------图片,视频审核-------------------------------------------
		/**
		 * 保存待审核图片
		 */
		void savePictureCheck(PictureCheck pictureCheck) ;
		/**
		 * 保存待审核视频
		 */
		void saveMovieCheck(MovieCheck movieCheck) ;
		/**
		 * 根据picId查询单个pictureCheck
		 */
		PictureCheck findPCById(Integer picId) ;
		/**
		 * 根据movId查询单个movieCheck
		 */
		MovieCheck findMCById(Integer movId) ;
		/**
		 * 根据checkTag分页查找所有的picCheck
		 */
		List<PictureCheck> findPCsByCheckTag(String checkTag,Integer currentPage,Integer maxResults) ;
		/**
		 * 根据checkTag分页查找所有的movCheck
		 */
		List<MovieCheck> findMCsByCheckTag(String checkTag,Integer currentPage,Integer maxResults) ;
		/**
		 * 根据userId分页查找所有的picCheck
		 */
		List<PictureCheck> findPCsByUserId(Integer userId,Integer currentPage,Integer maxResults) ;
		/**
		 * 根据userId分页查找所有的movCheck
		 */
		List<MovieCheck> findMCsByUserId(Integer userId,Integer currentPage,Integer maxResults) ; 
		/**
		 * 根据picId修改pc
		 */
		void updatePictureCheck(PictureCheck pictureCheck) ;
		/**
		 * 根据movId修改mc
		 */
		void updateMovieCheck(MovieCheck movieCheck) ;
		/**
		 * 根据checkTag和userId分页查找pc
		 */
		List<PictureCheck> findPCsByTagAndUserId(String checkTag,Integer userId,Integer currentPage,Integer maxResults) ;
		/**
		 * 根据checkTag和userId分页查找mc
		 */
		List<MovieCheck> findMCsByTagAndUserId(String checkTag,Integer userId,Integer currentPage,Integer maxResults) ;
		
}
