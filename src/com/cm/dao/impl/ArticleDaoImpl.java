package com.cm.dao.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.mapping.Array;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IArticleDao;
import com.cm.domain.Article;

@Repository("articleDao")
public class ArticleDaoImpl implements IArticleDao {

	@Resource(name = "hibernateTemplate")
	private HibernateTemplate hibernateTemplate;
	// 把hibernate session对象拿出来声明会报空指针异常，
	// getCurrentSessin()没有绑定线程？在配置文件中已经绑定了

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * 保存文章
	 */

	@Override
	public void saveArticle(Article article) {
		hibernateTemplate.save(article);

	}

	/**
	 * 通过文章id查找文章
	 */
	@Override
	public Article findById(int articleId) {
		Article article = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article WHERE artId=? ";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, articleId);
			article = (Article) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		session.close();
		return article;
	}

	/**
	 * 通过文章标题查找文章
	 * 
	 */
	@Override
	public Article findByTitle(String articleTitle) {
		Article article = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article WHERE artTitle=? ";
		try {
			Query query = session.createQuery(hql);
			query.setParameter(0, articleTitle);
			article = (Article) query.list().get(0);
		} catch (Exception e) {
			return null;
		}
		session.close();
		return article;
	}

	/**
	 * 通过作者名称查找文章
	 */
	@Override
	public List<Article> findByAuthor(String author) {
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		List<Article> articles = null;
		try {
			String hql = "SELECT artTitle FROM Article WHERE artAuthor=?";
			Query query = session.createQuery(hql);
			query.setParameter(0, author);
			articles = query.list();
		} catch (Exception e) {
			return null;
		}
		session.close();
		return articles;
	}

	/**
	 * 查找全部文章
	 */
	@Override
	public List<Article> findAllArticle() {

		return (List<Article>) hibernateTemplate.find("from article");

	}

	/**
	 * 查找全部文章的重载 按照倒序分页查询
	 * 
	 * @param currentPage 当前页面
	 * @param maxResults  每页最大条数
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Article> findAllArticle(Integer currentPage, Integer maxResults) {
		List<Article> articles = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("进入dao");
			Query query = session.createQuery(hql);
			// 设置开始查询的对象索引 当前页面-1 乘以每页最大条目数
			query.setFirstResult((currentPage - 1) * maxResults);
			// 设置每页最大条目数
			query.setMaxResults(maxResults);
			System.out.println(query);
			articles = query.list();

			System.out.println(articles);
		} catch (Exception e) {
			System.out.println("进入catch");
			return null;
		}
		System.out.println("正常");
		System.out.println(articles);
		session.close();
		return articles;

	}

	/**
	 * 查询指定内容
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> find(String content){
		List<String> list = new ArrayList<>() ;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "SELECT "+content+" FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("进入dao");
			Query query = session.createQuery(hql);
			// 设置开始查询的对象索引 当前页面-1 乘以每页最大条目数
			query.setFirstResult(0);
			// 设置每页最大条目数
			query.setMaxResults(10);
			System.out.println(query);
			list = query.list();

		} catch (Exception e) {
			System.out.println("进入catch");
			return null;
		}
		System.out.println("正常");
		session.close();
		return list;
		
		
		
	}
	
	
	/**
	 * 删除文章
	 */
	@Override
	public void deleteArticle(Article article) {
		hibernateTemplate.delete(article);

	}

	/**
	 * 更新文章信息
	 */
	@Override
	public void updateArticle(Article article) {
		hibernateTemplate.update(article);

	}

}
