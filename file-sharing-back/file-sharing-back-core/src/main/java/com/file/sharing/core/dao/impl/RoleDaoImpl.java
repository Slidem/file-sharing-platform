package com.file.sharing.core.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.file.sharing.common.security.Roles;
import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.RoleDao;
import com.file.sharing.core.entity.Role;

@Repository
public class RoleDaoImpl extends AbstractDaoImpl<Role> implements RoleDao {

	/**
	 * Dummy getRole
	 */
	@Override
	public Role getRole(Roles role) {
		TypedQuery<Role> query = entityManager.createQuery("from Role r where r.role=:role", Role.class)
				.setParameter("role", role);
		Role userRole = null;
		try {
			userRole = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userRole;
	}

}
