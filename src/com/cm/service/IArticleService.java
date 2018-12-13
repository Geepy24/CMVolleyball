package com.cm.service;

import java.util.List;

import com.cm.domain.Article;

/**
 * 	���µ�ҵ���ӿ�
 * @author Huangjiping
 *
 */
public interface IArticleService {

	/**
	 * 	��������
	 */
	void saveArticle(Article article) ;
	/**
	 * 	ɾ������
	 */
	void deleteArticle(Article article) ;
	/**
	 * 	�޸�����
	 */
	void updateArticle(Article article) ;
	
	/**ͨ��id��������
	 * 
	 */
	Article findById(int articleId) ;
	/**
	 * 	ͨ�������������
	 */
	Article findByTitle(String articleTitle) ;
	/**
	 * 	ͨ�����߲�������
	 */
	List<Article> findByAuthor(String author) ;
	/**
	 * 	������������
	 */
	List<Article> findAllArticle() ;
	/**
	 * 	��ҳ������������
	 * 
	 * @param currentPage
	 * @param maxResults
	 * @return
	 */
	List<Article> findAllArticle(Integer currentPage,Integer maxResults ) ;
	
}
