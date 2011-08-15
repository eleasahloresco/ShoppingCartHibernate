package com.onb.shoppingcart.service;

import java.util.List;

import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.exception.AdminServiceException;

public interface ProductService {

	public abstract List<Product> getAllProducts();

	void saveProduct(Product product) throws AdminServiceException;

	List<Product> getByCategory(Long categoryNumber);

}