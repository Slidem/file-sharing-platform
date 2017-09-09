package com.file.sharing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.file.sharing.dao.DbConstants;
import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.exceptions.InvalidRoleIdException;
import com.file.sharing.objects.Role;

@Component
public class BaseUserMapper implements RowMapper<BaseUser> {

	@Override
	public BaseUser mapRow(ResultSet rs, int rowNum) throws SQLException {
		int roleId = rs.getInt(DbConstants.UserTable.ROLE_COLUMN);

		Optional<Role> role = Role.fromId(roleId);
		if (!role.isPresent()) {
			throw new InvalidRoleIdException("No mathcing role found for roleId: " + roleId);
		}

		return new BaseUser.BaseClientBuilder().setEmail(rs.getString(DbConstants.UserTable.EMAIL_COLUMN))
				.setId(rs.getInt(DbConstants.UserTable.ID_COLUMN))
				.setPassword(rs.getString(DbConstants.UserTable.PASSWORD_COLUMN))
				.setRole(role.get())
				.build();
	}
}
