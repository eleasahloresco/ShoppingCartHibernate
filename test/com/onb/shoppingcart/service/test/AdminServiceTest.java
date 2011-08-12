package com.onb.shoppingcart.service.test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.AdminService;
import com.onb.shoppingcart.service.impl.AdminServiceImpl;


public class AdminServiceTest {
	
	private AdminService adminService;
	
	@Before
	public void setUp(){
		CategoryDao categoryDao = mock(CategoryDao.class);
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category());
		categories.add(new Category());
		
		when(categoryDao.getAll()).thenReturn(categories);
		AdminServiceImpl adminServiceImpl = new AdminServiceImpl();
		adminServiceImpl.setCategoryDao(categoryDao);
		adminService = adminServiceImpl;
	}
	
	@Test
	public void adminShouldAddCategories() {
		Category category3 = new Category();
		category3.setName("category 3");
//		
//		adminService.addCategory(category3);
//		categoryDao.getAll()
	}
	
}
