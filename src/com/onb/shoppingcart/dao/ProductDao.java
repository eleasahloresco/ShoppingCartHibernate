package com.onb.shoppingcart.dao;

import java.util.List;

import com.onb.shoppingcart.domain.Product;

public interface ProductDao extends GenericDao<Product, Long> {

	Product getByName(String name);

	List<Product> getAllProductsWithQuantityGreaterThanZero(Long id);

	List<Product> getByCategory(Long categoryId);

}
