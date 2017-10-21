package com.file.sharing.jms.events;

import java.io.Serializable;
import java.util.Objects;

import com.file.sharing.jms.commons.object.JmsUserInfo;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
public class UserCreatedEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2615865169004198591L;

	private final JmsUserInfo userInfo;

	public UserCreatedEvent(JmsUserInfo userInfo) {
		this.userInfo = Objects.requireNonNull(userInfo);
	}

	public JmsUserInfo getUserInfo() {
		return userInfo;
	}

}
