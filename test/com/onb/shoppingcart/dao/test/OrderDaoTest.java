package com.onb.shoppingcart.dao.test;

import static org.junit.Assert.assertFalse;
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

import com.onb.shoppingcart.dao.OrderDao;
import com.onb.shoppingcart.dao.UserDao;
import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.User;

@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class OrderDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private UserDao userDao;
	
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
	public void testSave(){
		User user = userDao.get(2L);		
		Order order = new Order();
		order.setUser(user);
		order.setAmount(new BigDecimal("546.23"));
		orderDao.save(order);
		
		assertNotNull(order.getId());
	}
	
	@Test
	public void testDelete(){
		Order order = orderDao.get(1L);
		orderDao.delete(order);
		
		assertNull(orderDao.get(1L));
	}
	
	@Test
	public void testUpdate(){
		Order order = orderDao.get(1L);
		BigDecimal prevOrderAmount = order.getAmount();
		System.out.println(prevOrderAmount);
		order.setAmount(new BigDecimal("600"));
		orderDao.update(order);
		
		assertFalse(prevOrderAmount.equals(order.getAmount()));
	}
	
	@Test
	public void testGet(){
		Order order = orderDao.get(1L);
		assertNotNull(order.getId());
	}
	
	@Test
	public void testGetAll(){
		User user = userDao.get(2L);		
		Order order = new Order();
		order.setUser(user);
		order.setAmount(new BigDecimal("546.23"));
		orderDao.save(order);
		
		List<Order> orders = orderDao.getAll();
		assertTrue(orders.contains(order));
	}	
	
}
