package com.file.sharing.common.security;

public enum Roles {

	ROLE_GUEST(1), ROLE_USER(2), ROLE_ADMIN(3);

	private int id;

	private Roles(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
