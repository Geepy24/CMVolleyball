package com.cm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cm.dao.IArticleDao;
import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.service.IArticleService;

import management.web.action.articleAction;
@Service("articleService")
public class ArticleServiceImpl implements IArticleService {

	@Autowired
	private IArticleDao articleDao ;
	
	@Override
	public Article findById(int articleId) {
		return articleDao.findById(articleId) ;
		
	}

	@Override
	public Article findByTitle(String articleTitle) {
		return articleDao.findByTitle(articleTitle);
	}

	@Override
	public List<Article> findByAuthor(String author) {
		return articleDao.findByAuthor(author);
	}

	@Override
	public List<Article> findAllArticle() {

		return articleDao.findAllArticle();
	}
	@Override
	public List<Article> findAllArticle(Integer currentPage, Integer maxResults) {
		
		return articleDao.findAllArticle(currentPage, maxResults);
	}
	@Override
	public void saveArticle(Article article) {
		articleDao.saveArticle(article) ;
		
	}
	

	@Override
	public void deleteArticle(Article article) {
		articleDao.deleteArticle(article);
		
	}

	@Override
	public void updateArticle(Article article) {
		articleDao.updateArticle(article); 
		
	}

	@Override
	public List<String> find(String content) {
		return articleDao.find(content);
	}

	@Override
	public void saveDraft(Draft draft) {

		articleDao.saveDraft(draft);
		
		
	}

	
	@Override
	public Draft findDraftById(int draId) {
		return articleDao.findDraftById(draId) ;
	}

	@Override
	public List<Draft> findAllDraft(Integer authorId ,Integer currentPage, Integer maxResults) {

		return articleDao.findAllDraft(authorId,currentPage,maxResults);
	}

	

}
