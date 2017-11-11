package com.file.sharing.common.security;

public enum Roles {

	GUEST(1), USER(2), ADMIN(3);

	private int id;

	private Roles(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

}
