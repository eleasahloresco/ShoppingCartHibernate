package com.onb.shoppingcart.test;

import javax.sql.DataSource;

import junit.framework.Assert;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.IDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.onb.shoppingcart.dao.CategoryDao;
import com.onb.shoppingcart.domain.Category;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:ApplicationContext.xml"})
public class CategoryDaoTest{
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private CategoryDao categoryDao;
	
	protected IDatabaseTester dbTester;
	
	@Before
	public void setUp() throws Exception{
		dbTester = new DataSourceDatabaseTester(dataSource);
		
		IDataSet dataSet = new FlatXmlDataSet(getClass().getResource("dateset.xml"));
		dbTester.setDataSet(dataSet);
		dbTester.onSetup();
	}
	
	@After
	public void tearDown() throws Exception{
		dbTester.onTearDown();
	}
	
//	private IDatabaseConnection getConnection() throws Exception{
//		Connection con = (Connection) dataSource.getConnection();
//		DatabaseMetaData databaseMetaData = (DatabaseMetaData) con.getMetaData();
//		IDatabaseConnection connection = new DatabaseConnection(con,
//				databaseMetaData.getUserName().toUpperCase());
//		return connection;
//	}
//	
//	private IDataSet getDataSet() throws Exception{
//		File file = new File("resources/dataset.xml");
//		return new FlatXmlDataSet(file);
//	}
	
	@Test
	public void testAdd(){
		Category category = new Category();
		category.setName("Food");
		categoryDao.save(category);
		
		Assert.assertNotNull(category.getId());
	}
}
