package com.onb.shoppingcart.service;

import java.util.List;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.service.exception.AdminServiceException;

public interface CategoryService {

	public abstract List<Category> getAllCategories();

	void saveCategory(Category category) throws AdminServiceException;

	Category getCategory(Long id);

}