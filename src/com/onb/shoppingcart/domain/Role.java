package com.onb.shoppingcart.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Role extends AbstractModel{

	@Column(unique = true, nullable = false)
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
