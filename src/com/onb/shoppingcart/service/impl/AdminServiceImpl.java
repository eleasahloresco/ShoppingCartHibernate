package com.onb.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.service.AdminService;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

	private CategoryDao categoryDao;

	@Autowired
	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}
	
	
}
