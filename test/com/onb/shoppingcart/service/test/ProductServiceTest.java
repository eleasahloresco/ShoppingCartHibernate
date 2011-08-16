package com.onb.shoppingcart.service.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.impl.ProductServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

	private ProductServiceImpl productService = new ProductServiceImpl();
		
	@Mock
	private ProductDao productDao;
	
	@Before 
	public void setUp(){
		productService.setProductDao(productDao);
	}
	
	@Test
	public void shouldGetAllProducts() throws Exception{
		List<Product> products = new ArrayList<Product>();
		products.add(new Product());
		products.add(new Product());
		when(productDao.getAll()).thenReturn(products);
		
		List<Product> retrievedProducts = productService.getAll();
		assertEquals(retrievedProducts.size(), 2);
		
	}
	
}
