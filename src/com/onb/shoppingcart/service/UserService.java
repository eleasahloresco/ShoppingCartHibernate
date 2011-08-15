package com.onb.shoppingcart.service;

import com.onb.shoppingcart.domain.User;

public interface UserService {

	public abstract User getUserByUsername(String username);

}