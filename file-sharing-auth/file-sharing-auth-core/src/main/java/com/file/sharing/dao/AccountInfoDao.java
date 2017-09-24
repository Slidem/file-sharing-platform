package com.file.sharing.dao;

import java.util.Optional;

import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

public interface AccountInfoDao {

	Optional<AccountInfo> findAccountInfo(AccountStatus status, AccountType type);

	AccountInfo insertAccountInfo(AccountStatus status, AccountType type);

}
