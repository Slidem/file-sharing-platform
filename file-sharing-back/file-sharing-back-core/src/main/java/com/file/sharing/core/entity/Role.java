package com.file.sharing.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.file.sharing.common.security.Roles;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "roles", schema = "public")
public class Role {

	@Id
	private Integer id;

	@Column(name = "role", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Roles roleType;

	public Role() {
	}

	public Role(Integer id, Roles role) {
		this.id = id;
		this.roleType = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Roles getRoleType() {
		return roleType;
	}

	public void setRoleType(Roles roleType) {
		this.roleType = roleType;
	}


}
