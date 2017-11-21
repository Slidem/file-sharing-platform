package com.file.sharing.core.jms.event;

import java.io.Serializable;

/**
 * 
 * @author Aioanei Andrei
 *
 */

public final class UserCreatedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final Integer userId;

	private final String email;

	public UserCreatedEvent(Integer userId, String email) {
		this.userId = userId;
		this.email = email;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public String toString() {
		return "UserCreatedEvent [userId=" + userId + ", email=" + email + "]";
	}
}
