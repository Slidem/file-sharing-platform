package com.file.sharing.entities;

import static java.util.Objects.requireNonNull;

import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

public class AccountInfo {

	private final Integer id;

	private final AccountStatus accountStatus;

	private final Integer subscriptionId;

	private AccountInfo(Integer id, AccountStatus accountStatus, Integer subscriptionId) {
		this.id = id;
		this.accountStatus = accountStatus;
		this.subscriptionId = subscriptionId;
	}

	public Integer getId() {
		return id;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public Integer getSubscriptionId() { return subscriptionId; }

	public static AccountInfo newInstance(AccountStatus accountStatus, Integer subscriptionId) {
		return new AccountInfo(null, requireNonNull(accountStatus), requireNonNull(subscriptionId));
	}

	public static AccountInfo newInstance(Integer id, AccountStatus accountStatus, Integer subscriptionId) {
		return new AccountInfo(requireNonNull(id), requireNonNull(accountStatus), requireNonNull(subscriptionId));
	}

}
