package com.onb.shoppingcart.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends GenericDaoImpl<Category, Long> implements CategoryDao {
	
	public CategoryDaoImpl(){
		super(Category.class);
	}
	
	@Override
	public Category findByName(String categoryName){
		String sqlQuery = "from Category category where category.name = :categoryName";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("categoryName", categoryName);
		return (Category) query.list().get(0);
	}
}
