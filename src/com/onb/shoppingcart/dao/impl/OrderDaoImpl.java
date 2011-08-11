package com.onb.shoppingcart.dao.impl;

import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.OrderDao;
import com.onb.shoppingcart.domain.Order;

@Repository("orderDao")
public class OrderDaoImpl extends AbstractDao<Order, Long> implements OrderDao {

	protected OrderDaoImpl() {
		super(Order.class);
	}

}
