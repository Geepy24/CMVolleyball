package com.cm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.cm.dao.IArticleDao;
import com.cm.domain.Article;
import com.cm.domain.Draft;
import com.cm.domain.Dustbin;
import com.cm.domain.User;

import management.web.action.dustbinAction;

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
	 * 分页查找指定用户的所有文章，每页10条
	 */
	public List<Article> findByUser(Article article,Integer currentPage,Integer maxResults){
		
		
		return hibernateTemplate.findByExample(article, (currentPage-1)*maxResults, maxResults) ;
		
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
//----------------------草稿箱操作----------------------
	@Override
	public void saveDraft(Draft draft) {
		hibernateTemplate.save(draft) ;
		
	}

	@Override
	public Draft findDraftById(int draId) {
		return (Draft) hibernateTemplate.find("From Draft WHERE draId=?", draId).get(0) ;
		
	}
	/**
	 * 	统计所有文章数目
	 */
	@Override
	public Long AllArticleNumber() {
		
		return  (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Article ").get(0) ;
				
	}
	/**
	 * 	根据用户id统计所有文章数目
	 */
	@Override
	public Long AllArticleNumber(Integer userId) {
		User user = new User() ;
		user.setUserId(userId);
		String sql = "SELECT COUNT(*) FROM Article WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
				
	}

	/**
	 * 	分页查询，find的分页方法
	 */
	@Override
	public List<Draft> findAllDraft(Draft draft , Integer currentPage, Integer maxResults) {
		
		
		return hibernateTemplate.findByExample(draft, (currentPage-1)*maxResults, maxResults) ;
		
		
	}
	/**
	 * 	根据authorId统计总共数目
	 */
	@Override
	public Long AllDraftNumber(Integer authorId) {
		User user = new User() ;
		user.setUserId(authorId);
		String sql = "SELECT COUNT(*) FROM Draft WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
				
	}
	@Override
	public void deleteDraft(Integer draId) {
		hibernateTemplate.delete(this.findDraftById(draId));
		
	}

	
	//------------------回收站操作--------------------------
	
	/**
	 * 通过id 查找
	 */
	@Override
	public Dustbin findDustbinById(Integer dustId) {
		
		return (Dustbin) hibernateTemplate.find("FROM Dustbin where dustId=?",dustId).get(0) ;
	}
	
	@Override
	public void saveDustbin(Dustbin dustbin) {
		
		hibernateTemplate.save(dustbin) ;
		
	}
	/**
	 * 无条件的分页
	 */

	@SuppressWarnings("unchecked")
	@Override
	public List<Dustbin> findAllDustbin(Integer currentPage, Integer MAXRESULTS) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Dustbin.class) ;
		return (List<Dustbin>) hibernateTemplate.findByCriteria(criteria, (currentPage-1)*MAXRESULTS, MAXRESULTS) ;
	}
	/**
	 *	查找总数
	 */
	@Override
	public Long AllDustbinNumber() {
		return (Long) hibernateTemplate.find("SELECT COUNT(*) FROM Dustbin").get(0);
	}
	/**
	 *	根据id查找总数
	 */
	@Override
	public Long AllDustbinNumber(Integer userId) {
		User user = new User() ;
		user.setUserId(userId);
		String sql = "SELECT COUNT(*) FROM Dustbin WHERE user=:id" ;
		String param = "id" ;
		User value = user  ;
		return  (Long) hibernateTemplate.findByNamedParam(sql, param, value).get(0) ;
	}
	
	
	/**
	 * 	彻底删除
	 */
	@Override
	public void deleteDustbin(Integer dustId) {
//		
//		Dustbin dustbin = this.findDustbinById(dustId) ;
//		dustbin.setUser(null);
//		hibernateTemplate.update(dustbin);
//		
//		System.out.println("解除关系");
//		
//		System.out.println(this.findDustbinById(dustId).getUser() == null);
		
		hibernateTemplate.delete(this.findDustbinById(dustId));
		
			
	}

	@Override
	public List<Dustbin> findAllDustbinByUser(Dustbin dustbin, Integer currentPage, Integer maxresults) {
		return hibernateTemplate.findByExample(dustbin, (currentPage-1)*maxresults, maxresults);
	}
	//修改草稿
	@Override
	public void updateDraft(Draft draft) {
		hibernateTemplate.update(draft);
		
	}


}
