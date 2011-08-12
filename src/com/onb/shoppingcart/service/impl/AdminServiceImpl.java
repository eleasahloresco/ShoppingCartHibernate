package com.onb.shoppingcart.service.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.AdminService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	private CategoryDao categoryDao;
	
	private ProductDao productDao;

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	@Override
	public List<Category> getAllCategories() {
		return categoryDao.getAll();
	}

	@Override
	public void saveCategory(Category category) throws AdminServiceException {
		if(category == null){
			throw new AdminServiceException("Category is Empty");
		}
		String sqlQuery = "from Category category where category.name = '" + category.getName() + "'";
		List<Category> categories = categoryDao.getByCriteria(sqlQuery);
		
		if(categories.contains(category)){
			throw new AdminServiceException("Category already Exist!");
		}
		
		categoryDao.save(category);
	}
	
	@Override
	public List<Product> getAllProducts(){
		return productDao.getAll();
	}
	
	@Override
	public void saveProduct(Product product){
		productDao.save(product);
	}
		
}
