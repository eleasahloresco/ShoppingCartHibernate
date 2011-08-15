package com.onb.shoppingcart.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OrderTable")
public class Order extends AbstractModel{

	@ManyToOne
	private User user;

	private BigDecimal amount = BigDecimal.ZERO;

	@OneToMany
	@JoinTable(name = "Order_OrderDetails",
			joinColumns = { @JoinColumn(name = "order_id")},
			inverseJoinColumns = { @JoinColumn(name = "order_detail_id")})
			private List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();

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

	public void updateListOfOrderDetails(OrderDetail orderDetail){
		if(getOrderDetails().isEmpty()){
			getOrderDetails().add(orderDetail);
		}
		else{
			orderDetails.add(orderDetail);
			//find order with the same product then increment
			reArrangeArrayListToAvoidDuplication();
		}
		amount = amount.add(orderDetail.getUnitPrice());
	}

	private void reArrangeArrayListToAvoidDuplication() {
		List<OrderDetail> newOrderDetails = new ArrayList<OrderDetail>();
		int index = 0;
		
		for (OrderDetail o : getOrderDetails()) {
			if(newOrderDetails.contains(o)){
				index = newOrderDetails.indexOf(o);
				newOrderDetails.get(index).increaseQuantity(newOrderDetails.get(index).getQuantity());
			}
			else{
				newOrderDetails.add(o);
			}
		}
		this.orderDetails = newOrderDetails;
	}
	
	public void removeOrderDetail(OrderDetail orderDetail) {
		getOrderDetails().remove(orderDetail);
		amount = amount.subtract(orderDetail.getUnitPrice());
	}
	
	public void removeAllOrderDetails() {
		getOrderDetails().removeAll(orderDetails);
		amount = BigDecimal.ZERO;
	}
	
	public BigDecimal computeToTalAmount(){
		BigDecimal totalAmount = BigDecimal.ZERO;
		for (OrderDetail orderDetail : getOrderDetails()) {
			BigDecimal orderDetailAmount = orderDetail.getUnitPrice().multiply(new BigDecimal(orderDetail.getQuantity()));
			totalAmount = totalAmount.add(orderDetailAmount);
		}
		return totalAmount;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		result = prime * result
				+ ((orderDetails == null) ? 0 : orderDetails.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (!amount.equals(other.amount))
			return false;
		if (orderDetails == null) {
			if (other.orderDetails != null)
				return false;
		} else if (!orderDetails.equals(other.orderDetails))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}
	
}
