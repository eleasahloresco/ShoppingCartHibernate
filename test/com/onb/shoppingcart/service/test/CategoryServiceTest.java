package com.onb.shoppingcart.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.runners.MockitoJUnitRunner;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.exception.AdminServiceException;
import com.onb.shoppingcart.service.impl.CategoryServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CategoryServiceTest {

	private CategoryServiceImpl categoryService = new CategoryServiceImpl();
	
	@Mock
	private CategoryDao categoryDao;
	
	@Before 
	public void setUp(){
		categoryService.setCategoryDao(categoryDao);
	}
	
	@Test
	public void shouldGetAllCategories() throws Exception{
		List<Category> categories = new ArrayList<Category>();
		categories.add(new Category());
		categories.add(new Category());
		when(categoryDao.getAll()).thenReturn(categories);
		
		List<Category> retrievedCategories = categoryService.getAllCategories();
		assertEquals(retrievedCategories.size(), 2);
		
	}
	
	@Test
	public void shouldAddCategory() throws AdminServiceException{
		Category category = new Category();
		category.setName("new category");
		categoryService.saveCategory(category);		
		
		//verify that the method was executed - but will fail if the service method was not call
		Mockito.verify(categoryDao).save(category);
	}
	
	@Test(expected =  MockitoException.class)
	public void shouldThrowExceptionWhenAddingExistingCategory() throws AdminServiceException{
		Category category = new Category();
		category.setName("new category");
		categoryService.saveCategory(category);
		Mockito.verify(categoryDao).save(category);
		
		Category duplicateCategory = new Category();
		duplicateCategory.setName("new category");
				
		Mockito.doThrow(new AdminServiceException("Category already Exist!")).when(categoryDao).save(duplicateCategory);
		
		try{
			categoryService.saveCategory(duplicateCategory);
		}catch (AdminServiceException e) {
			e.getMessage();
		}
	}
}
