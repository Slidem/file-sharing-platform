package com.file.sharing.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.common.user.AccountType;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
@Entity
@Table(name = "acc_stats", schema="public")
public class AccStats {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "type")
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountType type;

	@Column(name = "status")
	@NotNull
	@Enumerated(EnumType.STRING)
	private AccountStatus status;

	/**
	 *
	 */
	public AccStats() {
	}

	/**
	 * @param type
	 * @param status
	 */
	public AccStats(AccountType type, AccountStatus status) {
		this.type = type;
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public void setType(AccountType type) {
		this.type = type;
	}

	public AccountStatus getStatus() {
		return status;
	}

	public void setStatus(AccountStatus status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
}
