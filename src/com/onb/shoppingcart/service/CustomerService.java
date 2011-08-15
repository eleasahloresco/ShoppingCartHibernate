package com.onb.shoppingcart.service;

import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.service.exception.AdminServiceException;

public interface CustomerService {

	public abstract void saveOrder(Order order) throws AdminServiceException;

}