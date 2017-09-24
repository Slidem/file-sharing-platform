package com.file.sharing.service;

import java.util.Optional;

import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

public interface AccountInfoService {

	public AccountInfo create(AccountStatus status, AccountType type);

	public Optional<AccountInfo> find(AccountStatus status, AccountType type);

	public AccountInfo createOrGet(AccountStatus status, AccountType type);

}
