package com.onb.shoppingcart.test.dao;

import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;

@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class CategoryDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CategoryDao categoryDao;
	
	@Before
	public void setUp() throws Exception{
		DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(), getDataSet());
	}
	
	private IDatabaseConnection getDatabaseConnection() throws DatabaseUnitException, SQLException{
		IDatabaseConnection connection = new DatabaseConnection(getConnection());
		return connection;
	}
	
	private Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	private IDataSet getDataSet() throws MalformedURLException, DataSetException{
		return new FlatXmlDataSetBuilder().build(new File("resources/dataset.xml"));
	}
	
	@Test
	public void testAdd(){
		Category category = new Category();
		category.setName("Shoes");
		categoryDao.save(category);
		
		assertNotNull(category.getId());
	}
	
	@Test
	public void testDelete(){
		Category category = categoryDao.get(1L);
		categoryDao.delete(category);
		
		assertNull(categoryDao.get(1L));
	}
	
	@Test
	public void testUpdate(){
		Category category = categoryDao.get(1L);
		category.setName("Candies");
		categoryDao.update(category);
		
		assertEquals("Candies", category.getName());
	}
	
	
	@Test
	public void testGet(){
		Category category = categoryDao.get(1l);
		assertNotNull(category.getId());
	}
	
	@Test
	public void testGetAll(){
		Category category = new Category();
		category.setName("Books");
		categoryDao.save(category);
		
		List<Category> categories = categoryDao.getAll();
		assertTrue(categories.contains(category));
		
	}
	
}
