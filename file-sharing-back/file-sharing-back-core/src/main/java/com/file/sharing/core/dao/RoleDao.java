package com.file.sharing.core.dao;

import com.file.sharing.common.security.Roles;
import com.file.sharing.core.entity.Role;

public interface RoleDao extends AbstractDao<Role> {

	/**
	 * 
	 * @param role
	 * @return
	 */
	public Role getRole(Roles role);

}
