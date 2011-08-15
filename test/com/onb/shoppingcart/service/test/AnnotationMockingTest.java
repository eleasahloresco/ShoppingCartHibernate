package com.onb.shoppingcart.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.impl.AdminServiceImpl;


public class AnnotationMockingTest {

	private AdminServiceImpl adminService = new AdminServiceImpl();

	private CategoryDao categoryDao;
//
//	@Before 
//	public void setUp(){
//		categoryDao = mock(CategoryDao.class);
//		adminService.setCategoryDao(categoryDao);
//	}

	@Test
	public void adminShouldAddCategories() {
//		List<Category> categories = new ArrayList<Category>();
//		categories.add(new Category());
//		categories.add(new Category());
//		Category category = new Category();
//		category.setName("new category");
//
//		when(categoryDao.getAll()).thenReturn(categories);
		//		when(categoryDao.save(category)).thenReturn(category);


//		List<Category> retrievedCategories = adminService.getAllCategories();
//		System.out.println(retrievedCategories.size());
//
//		assertEquals(retrievedCategories.size(), 2);

		//		
		//ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
		//verify(categoryDao, times(1)).save(captor.capture());
		//
		//final List<Category> params = captor.getAllValues();
		//assertEquals("new category", params.get(0).getName());
	}

}
