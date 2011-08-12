package com.onb.shoppingcart.dao;

import java.util.List;

public interface GenericDao<T, ID> {

	public void save(T item);
	
	public void update(T item);
	
	public void delete(T item);
	
	public T get(ID id);
	
	public List<T> getAll();

	public abstract List<T> getByCriteria(String sqlQuery);
	
}