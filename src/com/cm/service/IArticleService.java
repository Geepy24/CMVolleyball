package com.cm.service;

import java.util.List;

import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.User;

/**
 * 	文章的业务层接口
 * @author Huangjiping
 *
 */
public interface IArticleService {

	/**
	 * 	保存文章
	 */
	void saveArticle(Article article) ;
	/**
	 * 	删除文章
	 */
	void deleteArticle(Article article) ;
	/**
	 * 	修改文章
	 */
	void updateArticle(Article article) ;
	
	/**通过id查找文章
	 * 
	 */
	Article findById(int articleId) ;
	/**
	 * 	通过标题查找文章
	 */
	Article findByTitle(String articleTitle) ;
	/**
	 * 	通过作者查找文章
	 */
	List<Article> findByAuthor(String author) ;
	/**
	 * 	查找所有文章
	 */
	List<Article> findAllArticle() ;
	/**
	 * 	分页查找所有文章
	 * 
	 * @param currentPage
	 * @param maxResults
	 * @return
	 */
	List<Article> findAllArticle(Integer currentPage,Integer maxResults ) ;
	/**
	 * 查找文章指定的内容
	 * @return 返回10条倒序的内容
	 */
	List<String> find(String content) ;
	/**
	 * 查找文章总数
	 */
	Long AllArticleNumber();
	/**
	 * 删除草稿
	 */
	void deleteDraft(Integer draId) ;
	/**
	 * 分页查找指定用户的所有文章，每页10条
	 */
	public List<Article> findByUserId(User user,Integer currentPage,Integer maxResults) ;
	//-----------草稿箱------------
	/**
	 * 保存至草稿箱
	 */
	void saveDraft(Draft draft) ;
	/**
	 * 	根据Id查找草稿
	 * @param draId
	 */
	Draft findDraftById(int draId);
	/**
	 * 根据authorName查找分页查找所有草稿
	 */
	List<Draft> findAllDraft(Draft draft ,Integer currentPage,Integer maxResults);
	/**
	 * 根据Id查找草稿总数
	 * @param authorId
	 * @return
	 */
	
	Long AllDraftNumber(Integer authorId);
	//--------------回收站操作--------------------
	/**
	 * 	保存回收站
	 */
	void saveDustbin(Dustbin dustbin) ;
	/**
	 * 	分页查找所有Dustbin
	 */
	List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS);
	/**
	 * 所有dustbin总数
	 */
	Long AllDustbinNumber() ;
	/**
	 * 	彻底删除
	 * @param dustId
	 */
	void deleteDustbin(Integer dustId);
	/**
	 * 通过id查找
	 * @param dustId
	 * @return
	 */
	Dustbin findDustbinById(Integer dustId) ;
}
