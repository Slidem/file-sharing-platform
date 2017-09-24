package com.file.sharing.objects;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author Alexandru
 *
 */
public enum Role {

	ROLE_GUEST(1), ROLE_USER(2), ROLE_ADMIN(3);

	private int id;

	private static final Map<Integer, Role> ROLE_ID_MAP = new HashMap<>();

	/**
	 * @param id
	 */
	private Role(int id) {
		this.id = id;
	}

	static {
		for (Role r : values()) {
			ROLE_ID_MAP.put(r.id, r);
		}
	}

	public static Optional<Role> fromId(Integer id) {
		return Optional.ofNullable(ROLE_ID_MAP.get(id));
	}

	public int getId() {
		return this.id;
	}

}
