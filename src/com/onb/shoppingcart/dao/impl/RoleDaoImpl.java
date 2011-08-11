package com.onb.shoppingcart.dao.impl;

import com.onb.shoppingcart.dao.RoleDao;
import com.onb.shoppingcart.domain.Role;

public class RoleDaoImpl extends AbstractDao<Role, Long> implements RoleDao {

	protected RoleDaoImpl() {
		super(Role.class);
	}

}
