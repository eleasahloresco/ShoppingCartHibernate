package com.onb.shoppingcart.dao.impl;

import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.OrderDetailDao;
import com.onb.shoppingcart.domain.OrderDetail;

@Repository("orderDetailDao")
public class OrderDetailDaoImpl extends GenericDaoImpl<OrderDetail, Long> implements OrderDetailDao {

	protected OrderDetailDaoImpl() {
		super(OrderDetail.class);
	}

}
