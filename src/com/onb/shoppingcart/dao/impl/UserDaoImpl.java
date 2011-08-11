package com.onb.shoppingcart.dao.impl;

import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.UserDao;
import com.onb.shoppingcart.domain.User;

@Repository("userDao")
public class UserDaoImpl extends AbstractDao<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}
	
}
