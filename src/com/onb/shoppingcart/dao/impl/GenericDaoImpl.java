package com.onb.shoppingcart.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;

import com.onb.shoppingcart.dao.GenericDao;

public abstract class GenericDaoImpl<T, ID extends Serializable> implements GenericDao<T, ID>{

	private final Class<T> domainClass;
	
	private SessionFactory sessionFactory;
	
	protected GenericDaoImpl(Class<T> domainClass){
		this.domainClass = domainClass;
	}
	
	public final SessionFactory getSessionFactory(){
		return sessionFactory;
	}
	
	@Autowired
	public final void setSessionFactory(SessionFactory sessionFactory){
		this.sessionFactory = sessionFactory;
	}	
	
	@Override
	public void save(T item) {
		getSessionFactory().getCurrentSession().save(item);		
	}

	@Override
	public void update(T item) {
		getSessionFactory().getCurrentSession().update(item);		
	}

	@Override
	public void delete(T item) {
		getSessionFactory().getCurrentSession().delete(item);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T get(ID id) {
		return (T) sessionFactory.getCurrentSession().get(domainClass, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getAll() {
		Criteria criteria = getSessionFactory().getCurrentSession().createCriteria(domainClass);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getByCriteria(String sqlQuery){
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		return query.list();
	}
}
