package com.onb.shoppingcart.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.onb.shoppingcart.dao.UserDao;
import com.onb.shoppingcart.domain.User;

@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	protected UserDaoImpl() {
		super(User.class);
	}
	
	@Override
	public User getUserByUsername(String username){
		String sqlQuery = "from User user where user.username = :username";
		Query query = getSessionFactory().getCurrentSession().createQuery(sqlQuery);
		query.setParameter("username", username);
		return (User) query.list().get(0);
	}
	
}
