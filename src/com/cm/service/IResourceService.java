package com.cm.service;

import java.util.List;

import com.cm.domain.Resource;

/**
 * 	��Դ���ҵ���ӿ�
 * @author Huangjiping
 *
 */
public interface IResourceService {

	/**
	 * 	������Դ
	 */
	void saveResource(Resource resource) ;
	/**
	 * 	ͨ�����ʱ�䵹���ҳ����������Դ
	 */
	List<Resource> findAllResource(String tag , Integer currentPage,Integer maxResults) ;
	/**
	 * 	ͨ��������û�id��ʱ�䵹���ҳ����������Դ
	 */
	List<Resource> findAllResource(String tag , Integer userId ,Integer currentPage,Integer maxResults) ;
	
	/**
	 * 	ͨ���û�id��������
	 */
	Long AllResourceNumber(Integer userId);
	/**
	 * 	ͨ��id�ͷ��������Դ����
	 */
	Long AllResourceNumber(Integer userId , String tag);
	/**
	 * 	���ҷ����µ���Դ����
	 */
	Long AllResourceNumber(String tag);
	/**
	 * ������Դ����
	 */
	Long AllResourceNumber();
	/**
	 * ɾ����Դ
	 */
	void deleteResource(Integer resId) ;
	/**
	 * 	ͨ��id������Դ
	 */
	Resource findResourceById(Integer resId) ;
	/**
	 * ͨ��id������һ����¼��id
	 */
	Integer nextResourceId(Integer resId) ;
	/**
	 * ͨ��id������һ������¼��id
	 */
	Integer preResourceId(Integer resId) ;
}
