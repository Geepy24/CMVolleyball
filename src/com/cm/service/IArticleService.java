package com.cm.service;

import java.util.List;

import com.cm.domain.Article;

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
	
}
