package com.file.sharing.core.service;

import com.file.sharing.common.security.Roles;
import com.file.sharing.core.entity.Role;

public interface RoleService {

	/**
	 * 
	 * @param roles
	 * @return
	 */
	public Role getRole(Roles roles);
}
