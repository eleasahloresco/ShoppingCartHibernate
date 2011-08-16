package com.onb.shoppingcart.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Product;

@Repository("productDao")
public class ProductDaoImpl extends GenericDaoImpl<Product, Long> implements ProductDao {

	public ProductDaoImpl() {
		super(Product.class);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getByCategory(Long categoryId){
		String sqlQuery = "from Product product where product.category.id = :categoryId ";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("categoryId", categoryId);
		return query.list();
	}
	
	@Override
	public Product getByName(String name){
		String sqlQuery = "from Product product where product.name = :name";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("name", name);
		return (Product) query.list().get(0);
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Product> getAllProductsWithQuantityGreaterThanZero(Long id){
		String sqlQuery = "from Product product where product.category.id = :id and product.inventoryQuantity > 0";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("id", id);
		return query.list();
	}
}
