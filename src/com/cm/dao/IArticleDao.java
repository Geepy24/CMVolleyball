package com.cm.dao;

import java.util.List;

import com.cm.domain.Article;

public interface IArticleDao {
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
	 * 	��ҳ��������
	 */
	List<Article> findAllArticle(Integer currentPage,Integer maxResults ) ;

}
