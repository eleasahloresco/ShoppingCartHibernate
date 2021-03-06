package com.onb.shoppingcart.dao.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
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
import com.onb.shoppingcart.dao.OrderDetailDao;
import com.onb.shoppingcart.dao.ProductDao;
import com.onb.shoppingcart.domain.Order;
import com.onb.shoppingcart.domain.OrderDetail;
import com.onb.shoppingcart.domain.Product;

@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class OrderDetailDaoTest extends AbstractTransactionalJUnit4SpringContextTests{

	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private OrderDao orderDao;
	
	@Autowired
	private ProductDao productDao;
	
	@Autowired
	private OrderDetailDao orderDetailDao;
	
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
		Order order = orderDao.get(1L);
		Product product = productDao.get(1L);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantity(10);
		orderDetail.setUnitPrice(new BigDecimal("1000"));
		orderDetailDao.save(orderDetail);
		
		assertNotNull(orderDetail.getId());
	}
	
	@Test
	public void testDelete(){
		OrderDetail orderDetail = orderDetailDao.get(1L);
		orderDetailDao.delete(orderDetail);
		
		assertFalse(orderDetailDao.getAll().contains(orderDetail));	
	}
	
	@Test
	public void testUpdate(){
		OrderDetail orderDetail = orderDetailDao.get(1L);
		Integer prevOrderDetailQuantity = orderDetail.getQuantity();
		orderDetail.setQuantity(20);
		orderDetailDao.update(orderDetail);
		
		assertFalse(prevOrderDetailQuantity.equals(orderDetail.getQuantity()));
	}
	
	@Test
	public void testGet(){
		OrderDetail orderDetail = orderDetailDao.get(1L);
		assertNotNull(orderDetail.getId());
	}

	@Test
	public void testGetAll(){
		Order order = orderDao.get(1L);
		Product product = productDao.get(1L);
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setOrder(order);
		orderDetail.setProduct(product);
		orderDetail.setQuantity(10);
		orderDetail.setUnitPrice(new BigDecimal("1000"));
		orderDetailDao.save(orderDetail);
		
		List<OrderDetail> orderDetails = orderDetailDao.getAll();
		assertTrue(orderDetails.contains(orderDetail));
	}
}
