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
	public List<Product> getAllProducts(){
		return productDao.getAll();
	}
	
	@Override
	@Transactional(readOnly = false)
	public void saveProduct(Product product) throws AdminServiceException{
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
	public List<Product> getProductByCategory(Long categoryNumber) {
		//queries should be in dao
		String sqlQuery = "from Product product where product.category.id = '" + categoryNumber + "'";
		return productDao.getByCriteria(sqlQuery);
	}

	@Override
	public Product getProduct(Long id) {
		return productDao.get(id);
	}

	@Override
	public Product getProductByName(String name) {
		String sqlQuery = "from Product product where product.name = '" + name + "'";
		return productDao.getByCriteria(sqlQuery).get(0);
	}
	
	@Override
	public List<Product> getAllProductsWithQuantityGreaterThanZero(Long categoryNumber) {
		String sqlQuery = "from Product product where product.category.id = '" + categoryNumber + "' " +
				"and product.inventoryQuantity > 0";
		return productDao.getByCriteria(sqlQuery);
	}
	
		
}
