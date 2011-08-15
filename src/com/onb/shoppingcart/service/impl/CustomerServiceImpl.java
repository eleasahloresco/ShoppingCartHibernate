package com.onb.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.OrderDao;
import com.onb.shoppingcart.dao.OrderDetailDao;
import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.CustomerService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Service("customerService")
@Transactional
public class CustomerServiceImpl implements CustomerService {

	private OrderDao orderDao;
	
	private OrderDetailDao orderDetailDao;
	
	private ProductDao productDao;

	@Autowired
	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}
	
	@Autowired
	public void setOrderDetailDao(OrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
	}

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void saveOrder(Order order) throws AdminServiceException{
		if(order == null){
			throw new AdminServiceException("Order is Empty");
		}
		orderDao.save(order);
		saveOrderDetails(order);
		
	}
	
	private void saveOrderDetails(Order order) throws AdminServiceException{
		List<OrderDetail> orderDetails = order.getOrderDetails();
		Product product = new Product();
				
		for (OrderDetail orderDetail : orderDetails) {
			product = orderDetail.getProduct();
			if(product.getInventoryQuantity() < orderDetail.getQuantity()){
				throw new AdminServiceException("orderDetail quantity is higher than inventory quantity");
			}
			
			orderDetail.setOrder(order);
			orderDetailDao.save(orderDetail);		
			updateProductQuantity(product, orderDetail);
		}
	}
	
	private void updateProductQuantity(Product product, OrderDetail orderDetail){//naming, productService
		Integer inventoryQuantity = 0;
		product = orderDetail.getProduct();
		
		inventoryQuantity = product.getInventoryQuantity() - orderDetail.getQuantity();
		product.setInventoryQuantity(inventoryQuantity);
		
		productDao.update(product);
	}
	
	
}
