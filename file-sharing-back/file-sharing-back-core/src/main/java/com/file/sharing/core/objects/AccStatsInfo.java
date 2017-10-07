package com.file.sharing.core.objects;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.common.user.AccountType;

/**
 * @author Alexandru
 *
 */
public class AccStatsInfo {

	private final AccountType type;

	private final AccountStatus status;

	/**
	 * @param type
	 * @param status
	 */
	public AccStatsInfo(AccountType type, AccountStatus status) {
		this.type = type;
		this.status = status;
	}

	public AccountType getType() {
		return type;
	}

	public AccountStatus getStatus() {
		return status;
	}

}
