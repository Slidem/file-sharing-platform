package com.file.sharing.jms.commons.object;

import java.io.Serializable;
import java.time.Instant;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
public class JmsUserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7869813705981077126L;

	private Integer id;

	private String email;

	private Instant creationTime;

	public JmsUserInfo(Integer id, String email, Instant creationTime) {
		this.id = id;
		this.email = email;
		this.creationTime = creationTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Instant getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Instant creationTime) {
		this.creationTime = creationTime;
	}

}
