package com.cm.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cm.domain.Resource;
public interface IResourceDao {
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
	Integer nextResourceId(Integer resId,String tag) ;
	/**
	 * ͨ��id������һ������¼��id
	 */
	Integer preResourceId(Integer resId,String tag);
}
