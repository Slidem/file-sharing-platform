package com.file.sharing.entities;

import static java.util.Objects.requireNonNull;

import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

public class AccountInfo {

	private final Integer id;

	private final AccountStatus accountStatus;

	private final AccountType accountType;

	private AccountInfo(Integer id, AccountStatus accountStatus, AccountType accountType) {
		this.id = id;
		this.accountStatus = accountStatus;
		this.accountType = accountType;
	}

	public Integer getId() {
		return id;
	}

	public AccountStatus getAccountStatus() {
		return accountStatus;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public static AccountInfo newInstance(AccountStatus accountStatus, AccountType accountType) {
		return new AccountInfo(null, requireNonNull(accountStatus), requireNonNull(accountType));
	}

	public static AccountInfo newInstance(Integer id, AccountStatus accountStatus, AccountType accountType) {
		return new AccountInfo(requireNonNull(id), requireNonNull(accountStatus), requireNonNull(accountType));
	}

}
