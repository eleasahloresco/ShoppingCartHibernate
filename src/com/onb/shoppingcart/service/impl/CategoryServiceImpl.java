package com.onb.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.CategoryService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl implements CategoryService {

	private CategoryDao categoryDao;
	
	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public void saveCategory(Category category) throws AdminServiceException {
		if(category == null){
			throw new AdminServiceException("Category is Empty");
		}
		
		Category categoryName = categoryDao.findByName(category.getName());
		
		if(categoryName != null){
			throw new AdminServiceException("Category already Exist!");
		}
		
		categoryDao.save(category);
	}
	
	@Override
	public Category getCategory(Long id) {
		return categoryDao.get(id);
	}
	
	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAll();
	}
	
}
