package com.onb.shoppingcart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Product;
import com.onb.shoppingcart.service.ProductService;
import com.onb.shoppingcart.service.exception.AdminServiceException;

@Service("productService")
@Transactional(readOnly = true)
public class ProductServiceImpl implements ProductService {

	private ProductDao productDao;

	@Autowired
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	@Override
	public List<Product> getAll(){
		return productDao.getAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void save(Product product) throws AdminServiceException{
		if(product == null){
			throw new AdminServiceException("Product is Empty");
		}
		List<Product> products = productDao.getAll();
		
		if(products.contains(product)){
			throw new AdminServiceException("Product already Exist!");
		}
		
		productDao.save(product);
	}

	@Override
	public List<Product> getByCategory(Long categoryId) {
		return productDao.getByCategory(categoryId);
	}

	@Override
	public Product get(Long id) {
		return productDao.get(id);
	}

	@Override
	public Product getByName(String name) {
		return productDao.getByName(name);
	}
	
	@Override
	public List<Product> getAllProductsWithQuantityGreaterThanZero(Long id) {
		return productDao.getAllProductsWithQuantityGreaterThanZero(id);
	}
	
		
}
