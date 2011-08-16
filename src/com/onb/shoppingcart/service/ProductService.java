package com.onb.shoppingcart.service;

import java.util.List;

import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.exception.AdminServiceException;

public interface ProductService {

	List<Product> getAll();

	void save(Product product) throws AdminServiceException;

	List<Product> getByCategory(Long categoryId);

	Product get(Long id);

	Product getByName(String name);

	List<Product> getAllProductsWithQuantityGreaterThanZero(Long id);

}