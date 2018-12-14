package com.cm.dao;

import java.util.List;

import com.cm.domain.Article;
import com.cm.domain.Draft;

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
	/**
	 * ��������ָ��������
	 * @return ����10�����������
	 */
	List<String> find(String content) ;
//---------------�ݸ���Ĳ���------------------
	
	/**
	 * �������ݸ���
	 */
	void saveDraft(Draft draft) ;
	/**
	 * 	����Id���Ҳݸ�
	 * @param draId
	 */
	Draft findDraftById(int draId);
	/**
	 * ����Id��ҳ�������вݸ�
	 */
	List<Draft> findAllDraft(Integer authorId,Integer currentPage,Integer maxResults);
		
	
}
