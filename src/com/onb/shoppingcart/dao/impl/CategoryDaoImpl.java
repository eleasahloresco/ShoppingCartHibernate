package com.onb.shoppingcart.dao.impl;

import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;

@Repository("categoryDao")
public class CategoryDaoImpl extends AbstractDao<Category, Long> implements CategoryDao {
	
	public CategoryDaoImpl(){
		super(Category.class);
	}

}
