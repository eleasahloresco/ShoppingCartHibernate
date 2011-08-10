package com.onb.shoppingcart.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product{

	@Id
	@GeneratedValue
	@Column(name = "key_product")
	private BigDecimal productNumber;
	
	@ManyToOne
	@Column(name = "key_category")
	private Category category;
	
	@Column(name = "product_name", nullable = false, unique = true)
	private String productName;
	
	@Column(name = "inventory_qty", nullable = false)
	private BigDecimal inventoryQuantity;
	
	@Column(name = "unit_price", nullable = false)
	private BigDecimal unitPrice;

	public BigDecimal getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(BigDecimal productNumber) {
		this.productNumber = productNumber;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getInventoryQuantity() {
		return inventoryQuantity;
	}

	public void setInventoryQuantity(BigDecimal inventoryQuantity) {
		this.inventoryQuantity = inventoryQuantity;
	}

	public BigDecimal getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(BigDecimal unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	
}
