package com.onb.shoppingcart.dao;

import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;

public interface CategoryDao extends GenericDao<Category, Long>{

	Category findByName(String categoryName);
	
}
