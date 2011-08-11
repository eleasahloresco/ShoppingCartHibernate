package com.onb.shoppingcart.test;


import java.io.FileInputStream;

import javax.sql.DataSource;

import org.dbunit.DatabaseTestCase;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.XmlDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.onb.shoppingcart.domain.Category;


public class CategoryDaoTest extends DatabaseTestCase{
	
	@Autowired
	private DataSource dataSource;
	
	protected IDatabaseConnection getConnection() throws Exception {
		return new DatabaseConnection(dataSource.getConnection());
	}

	@Override
	protected IDataSet getDataSet() throws Exception {
		return new XmlDataSet(new FileInputStream("resources/dataset.xml"));
	}
	
	protected DatabaseOperation getSetUpOperation() throws Exception{
		return DatabaseOperation.CLEAN_INSERT;
	}
	
	protected DatabaseOperation getTearDownOperation() throws Exception{
		return DatabaseOperation.NONE;
	}
	
	@Test
	public void addCategoryTest(){
		Category category = new Category();
		category.setName("Food");
		
		assertNotNull(category.getId());
	}
}
