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
	
	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	
		
}
