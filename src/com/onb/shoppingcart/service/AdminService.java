package com.onb.shoppingcart.service;

import java.util.List;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.exception.AdminServiceException;

public interface AdminService {

	public abstract List<Category> getAllCategories();

	public abstract void saveCategory(Category category) throws AdminServiceException;

	public abstract void saveProduct(Product product) throws AdminServiceException;

	public abstract List<Product> getAllProducts();

	public abstract Category getCategory(Long id);

}