package com.onb.shoppingcart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.onb.shoppingcart.dao.UserDao;
import com.onb.shoppingcart.domain.User;
import com.onb.shoppingcart.service.UserService;

@Service("currentUserService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserDao userDao;

	@Autowired	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User getUserByUsername(String username) {
		String sqlQuery = "from User user where user.username = '" + username + "'";
		return userDao.getByCriteria(sqlQuery).get(0);
	}
	
	
}
