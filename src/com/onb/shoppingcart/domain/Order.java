package com.onb.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Order extends AbstractModel{

	@ManyToMany
	@JoinTable(name = "Order_User",
			joinColumns = { @JoinColumn(name = "id")},
			inverseJoinColumns = { @JoinColumn(name = "id")})
	private User user;
	
	private BigDecimal amount = BigDecimal.ZERO;
	
	@OneToMany
	@JoinTable(name = "Order_OrderDetails",
			joinColumns = { @JoinColumn(name = "id")},
			inverseJoinColumns = { @JoinColumn(name = "id")})
	private List<OrderDetail> orderDetails;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	
	
}
