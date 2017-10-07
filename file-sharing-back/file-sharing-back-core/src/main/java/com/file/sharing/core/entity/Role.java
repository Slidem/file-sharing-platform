package com.file.sharing.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.file.sharing.common.security.Roles;

@Entity
@Table(name = "roles", schema = "public")
public class Role {

	@Id
	private Integer id;

	@Column(name = "role", length = 50)
	@NotNull
	@Enumerated(EnumType.STRING)
	private Roles role;

	public Role() {
	}

	public Role(Integer id, Roles role) {
		this.id = id;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Roles getRole() {
		return role;
	}

	public void setRole(Roles role) {
		this.role = role;
	}

}
