package com.onb.shoppingcart.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_product")
public class Product extends AbstractModel{

	@ManyToOne
	private Category category;
	
	@Column(nullable = false, unique = true)
	private String productName;
	
	@Column(nullable = false)
	private BigDecimal inventoryQuantity;
	
	@Column(nullable = false)
	private BigDecimal unitPrice;

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
