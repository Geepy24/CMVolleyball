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
	// ��hibernate session�����ó��������ᱨ��ָ���쳣��
	// getCurrentSessin()û�а��̣߳��������ļ����Ѿ�����

	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}

	/**
	 * ��������
	 */

	@Override
	public void saveArticle(Article article) {
		hibernateTemplate.save(article);

	}

	/**
	 * ͨ������id��������
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
	 * ͨ�����±����������
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
	 * ͨ���������Ʋ�������
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
	 * ����ȫ������
	 */
	@Override
	public List<Article> findAllArticle() {

		return (List<Article>) hibernateTemplate.find("from article");

	}

	/**
	 * ����ȫ�����µ����� ���յ����ҳ��ѯ
	 * 
	 * @param currentPage ��ǰҳ��
	 * @param maxResults  ÿҳ�������
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Article> findAllArticle(Integer currentPage, Integer maxResults) {
		List<Article> articles = null;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("����dao");
			Query query = session.createQuery(hql);
			// ���ÿ�ʼ��ѯ�Ķ������� ��ǰҳ��-1 ����ÿҳ�����Ŀ��
			query.setFirstResult((currentPage - 1) * maxResults);
			// ����ÿҳ�����Ŀ��
			query.setMaxResults(maxResults);
			System.out.println(query);
			articles = query.list();

			System.out.println(articles);
		} catch (Exception e) {
			System.out.println("����catch");
			return null;
		}
		System.out.println("����");
		System.out.println(articles);
		session.close();
		return articles;

	}

	/**
	 * ��ѯָ������
	 * @return
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<String> find(String content){
		List<String> list = new ArrayList<>() ;
		Session session = hibernateTemplate.getSessionFactory().getCurrentSession();
		String hql = "SELECT "+content+" FROM Article ORDER BY artId DESC ";
		try {
			System.out.println("����dao");
			Query query = session.createQuery(hql);
			// ���ÿ�ʼ��ѯ�Ķ������� ��ǰҳ��-1 ����ÿҳ�����Ŀ��
			query.setFirstResult(0);
			// ����ÿҳ�����Ŀ��
			query.setMaxResults(10);
			System.out.println(query);
			list = query.list();

		} catch (Exception e) {
			System.out.println("����catch");
			return null;
		}
		System.out.println("����");
		session.close();
		return list;
		
		
		
	}
	
	
	/**
	 * ɾ������
	 */
	@Override
	public void deleteArticle(Article article) {
		hibernateTemplate.delete(article);

	}

	/**
	 * ����������Ϣ
	 */
	@Override
	public void updateArticle(Article article) {
		hibernateTemplate.update(article);

	}

}
