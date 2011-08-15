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
import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.exception.AdminServiceException;
import com.onb.shoppingcart.service.impl.AdminServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class AdminServiceTest {
	
	private AdminServiceImpl adminService = new AdminServiceImpl();
	
	@Mock
	private CategoryDao categoryDao;
	
	@Mock
	private ProductDao productDao;
	
	@Before 
	public void setUp(){
		adminService.setCategoryDao(categoryDao);
		adminService.setProductDao(productDao);
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
	
	@Test(expected =  MockitoException.class)
	public void shouldThrowExceptionWhenAddingExistingCategory() throws AdminServiceException{
		Category category = new Category();
		category.setName("new category");
		adminService.saveCategory(category);
		Mockito.verify(categoryDao).save(category);
		
		Category duplicateCategory = new Category();
		duplicateCategory.setName("new category");
				
		Mockito.doThrow(new AdminServiceException("Category already Exist!")).when(categoryDao).save(duplicateCategory);
		
		try{
			adminService.saveCategory(duplicateCategory);
		}catch (AdminServiceException e) {
			e.getMessage();
		}
	}
	
	@Test
	public void adminShouldGetAllProducts() throws Exception{
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());
		when(productDao.getAll()).thenReturn(products);
		
		List<Product> retrievedProducts = adminService.getAllProducts();
		assertEquals(retrievedProducts.size(), 2);
		
	}

}
