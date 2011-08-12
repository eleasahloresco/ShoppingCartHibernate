package com.onb.shoppingcart.service.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import static org.mockito.Mockito.*;

import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.exception.AdminServiceException;
import com.onb.shoppingcart.service.exception.DuplicateValueException;
import com.onb.shoppingcart.service.impl.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
	
	private AdminServiceImpl adminService = new AdminServiceImpl();
	
	@Mock
	private CategoryDao categoryDao;
	
	@Before 
	public void setUp(){
		adminService.setCategoryDao(categoryDao);
	}
	
	@Test
	public void adminShouldGetAllCategories() throws Exception{
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category());
		categories.add(new Category());
		when(categoryDao.getAll()).thenReturn(categories);
		
		List<Category> retrievedCategories = adminService.getAllCategories();
		assertEquals(retrievedCategories.size(), 2);
		
	}
	
	@Test
	public void adminShouldAddCategory() throws AdminServiceException{
		Category category = new Category();
		category.setName("new category");
		adminService.saveCategory(category);		
		
		//verify that the method was executed - but will fail if the service method was not call
		Mockito.verify(categoryDao).save(category);
	}
	
	@Test(expected = AdminServiceException.class)
	public void shouldThrowExceptionWhenAddingExistingCategory() throws AdminServiceException{
		Category category = new Category();
		category.setName("new category");
		
		Category duplicateCategory = new Category();
		duplicateCategory.setName("new category");
				
		List<Category> categories = new ArrayList<Category>();
		categories.add(category);
		when(categoryDao.getAll()).thenReturn(categories);
		
		adminService.saveCategory(category);

		Mockito.verify(categoryDao).save(category);
		Mockito.doThrow(new AdminServiceException("Category already Exist!")).when(categoryDao).save(duplicateCategory);
	}
	

}
