package com.onb.shoppingcart.dao;

import java.util.List;

public interface GenericDao<T, ID> {

	void save(T item);
	
	void update(T item);
	
	void delete(T item);
	
	T get(ID id);
	
	List<T> getAll();

	List<T> getByCriteria(String sqlQuery);
	
}