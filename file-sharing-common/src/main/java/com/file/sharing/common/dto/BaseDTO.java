package com.file.sharing.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author Alexandru
 *
 */
@JsonInclude(Include.NON_NULL)
public class BaseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3379683796553245468L;

	protected int code;

	/**
	 * @return
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 */
	public void setCode(int code) {
		this.code = code;
	}

}
