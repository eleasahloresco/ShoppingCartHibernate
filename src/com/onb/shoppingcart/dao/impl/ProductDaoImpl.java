package com.onb.shoppingcart.dao.impl;

import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Product;

@Repository("productDao")
public class ProductDaoImpl extends AbstractDao<Product, Long> implements ProductDao {

	protected ProductDaoImpl() {
		super(Product.class);
	}
	
}
