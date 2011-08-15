package com.onb.shoppingcart.domain.test;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.domain.User;


public class OrderTest {

	@Test
	public void testAddOrderDetail(){
		Order order = createOrder();		
		order.updateListOfOrderDetails(createOrderDetail());
		
		assertTrue(order.getOrderDetails().size() == 1);
		assertEquals(new BigDecimal("100.50"), order.getAmount());
		
		order.updateListOfOrderDetails(createOrderDetail2());		
		assertTrue(order.getOrderDetails().size() == 2);
	}
	
	@Test
	public void testAddOrderDetailWithSameOrderDetail(){
		Order order = createOrder();		
		order.updateListOfOrderDetails(createOrderDetail());
		
		assertTrue(order.getOrderDetails().size() == 1);
		assertEquals(new BigDecimal("100.50"), order.getAmount());
		
		order.updateListOfOrderDetails(createOrderDetail());
		assertTrue(order.getOrderDetails().size() == 1);
		assertEquals(new BigDecimal("201.00"), order.getAmount());	
		
		assertEquals(new BigDecimal("20"), order.getOrderDetails().get(0).getQuantity());
	}
	
	@Test
	public void testRemoveOneOrderDetail(){
		Order order = createOrder();		
		OrderDetail orderDetail = createOrderDetail();
		order.updateListOfOrderDetails(orderDetail);
		order.removeOrderDetail(orderDetail);
		
		assertFalse(order.getOrderDetails().contains(orderDetail));
		assertEquals(new BigDecimal("0.00"), order.getAmount());
		assertTrue(order.getOrderDetails().size() == 0);
	}
	
	@Test
	public void testRemoveAll(){
		Order order = createOrder();		
		order.updateListOfOrderDetails(createOrderDetail());
		
		assertTrue(order.getOrderDetails().size() == 1);
		assertEquals(new BigDecimal("100.50"), order.getAmount());
		
		order.updateListOfOrderDetails(createOrderDetail2());		
		assertTrue(order.getOrderDetails().size() == 2);
		
		order.removeAllOrderDetails();
		assertTrue(order.getOrderDetails().size() == 0);
		assertEquals(new BigDecimal("0"), order.getAmount());
	}
	
	private OrderDetail createOrderDetail(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(createProduct());
		orderDetail.setQuantity(10);
		orderDetail.setUnitPrice(new BigDecimal("100.50"));
		
		return orderDetail;
	}
	
	private OrderDetail createOrderDetail2(){
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setProduct(createProduct2());
		orderDetail.setQuantity(10);
		orderDetail.setUnitPrice(new BigDecimal("100.50"));
		
		return orderDetail;
	}
	
	private Product createProduct(){
		Product product = new Product();
		product.setId(new Long("1"));
		product.setCategory(createCategory());
		product.setName("Pizza");
		product.setInventoryQuantity(10);
		product.setUnitPrice(new BigDecimal("100.50"));
		
		return product;
	}


	private Product createProduct2(){
		Product product = new Product();
		product.setId(new Long("2"));
		product.setCategory(createCategory());
		product.setName("Donut");
		product.setInventoryQuantity(1000);
		product.setUnitPrice(new BigDecimal("100.50"));
		
		return product;
	}
	
	private Category createCategory() {
		Category category = new Category();
		category.setId(new Long("1"));
		category.setName("Food");
		
		return category;
	}
	
	private Order createOrder(){
		Order order = new Order();
		order.setId(new Long("1"));
		order.setUser(createUser());
		
		return order;
	}
	
	private User createUser(){
		User user = new User();
		user.setId(new Long("1"));
		user.setUsername("cus1");
		user.setPassword("cus1");
		
		return user;
	}
}
