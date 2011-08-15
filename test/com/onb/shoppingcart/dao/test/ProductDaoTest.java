package com.onb.shoppingcart.dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.math.BigDecimal;
import java.net.MalformedURLException;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Category;
import com.onb.shoppingcart.domain.Product;

@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class ProductDaoTest extends AbstractTransactionalJUnit4SpringContextTests{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private CategoryDao categoryDao;

	@Before
	public void setUp() throws Exception{
		DatabaseOperation.CLEAN_INSERT.execute(getDatabaseConnection(), getDataSet());
	}
	
	private IDatabaseConnection getDatabaseConnection() throws DatabaseUnitException, SQLException{
		IDatabaseConnection connection = new DatabaseConnection(dataSource.getConnection());
		return connection;
	}
	
	private IDataSet getDataSet() throws MalformedURLException, DataSetException{
		return new FlatXmlDataSetBuilder().build(new File("resources/dataset.xml"));
	}

	
	@Test
	public void testAdd(){
		Category category = categoryDao.get(1L);
		Product product = new Product();
		product.setCategory(category);
		product.setName("Ice Cream");
		product.setInventoryQuantity(1000);
		product.setUnitPrice(new BigDecimal("10.50"));
		productDao.save(product);
		
		assertNotNull(product.getId());
	}
	
	@Test
	public void testDelete(){
		Product product = productDao.get(1L);
		productDao.delete(product);
		
		assertNull(productDao.get(1L));
	}
	
	@Test
	public void testUpdate(){
		Product product = productDao.get(1L);
		product.setName("Chicken");
		productDao.update(product);
		
		assertEquals("Chicken", product.getName());
	}
	
	@Test
	public void testGet(){
		Product product = productDao.get(1L);
		assertNotNull(product.getId());
	}
	
	@Test
	public void testGetAll(){
		Category category = categoryDao.get(1L);
		Product product = new Product();
		product.setCategory(category);
		product.setName("Ice Cream");
		product.setInventoryQuantity(1000);
		product.setUnitPrice(new BigDecimal("10.50"));
		productDao.save(product);
		
		List<Product> products = productDao.getAll();
		assertTrue(products.contains(product));
	}
	
	@Test
	public void testGetAllProductsWithSpecificCategory(){
		Category category = categoryDao.get(1L);
		Product product = new Product();
		product.setCategory(category);
		product.setName("Ice Cream");
		product.setInventoryQuantity(1000);
		product.setUnitPrice(new BigDecimal("10.50"));
		productDao.save(product);
		
		String sqlQuery = "from Product product where product.category.id = '" + category.getId() + "'";
		List<Product> products = productDao.getByCriteria(sqlQuery);
		assertTrue(products.contains(product));
		
	}
}
