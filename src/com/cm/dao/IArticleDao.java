package com.cm.dao;

import java.util.List;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;

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
	/**
	 * ������������
	 */
	Long AllArticleNumber();
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
	/**
	 * ����Id���Ҳݸ�����
	 * @param authorId
	 * @return
	 */
	Long AllDraftNumber(Integer authorId);
	/**
	 * ɾ���ݸ�
	 */
	void deleteDraft(Integer draId) ;
	
	
	//---------------------�ݸ������-------------------
	/**
	 *  ����ݸ������
	 * @param dustbin
	 */
	void saveDustbin(Dustbin dustbin);
	/**
	 * 	��ҳ��������Dustbin
	 */
	List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS);
	/**
	 * ����dustbin����
	 */
	Long AllDustbinNumber() ;
	/**
	 * 	����ɾ��
	 * @param dustId
	 */
	void deleteDustbin(Integer dustId);
	/**
	 * ͨ��id����
	 * @param dustId
	 * @return
	 */
	Dustbin findDustbinById(Integer dustId) ;
	
}
