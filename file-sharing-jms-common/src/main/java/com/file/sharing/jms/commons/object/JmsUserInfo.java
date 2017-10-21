package com.file.sharing.jms.commons.object;

import java.io.Serializable;

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

	public JmsUserInfo(Integer id, String email) {
		super();
		this.id = id;
		this.email = email;
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

}
