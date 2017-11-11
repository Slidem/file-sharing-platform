package com.file.sharing.core.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.sharing.common.security.Roles;
import com.file.sharing.core.dao.RoleDao;
import com.file.sharing.core.entity.Role;
import com.file.sharing.core.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;

	@Autowired
	public RoleServiceImpl(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role getRole(Roles role) {
		return roleDao.getRole(role);
	}

}
