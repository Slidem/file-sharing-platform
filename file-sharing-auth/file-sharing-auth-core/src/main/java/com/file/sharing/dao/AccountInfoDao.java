package com.file.sharing.dao;

import java.util.Optional;

import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

public interface AccountInfoDao {

	Optional<AccountInfo> findAccountInfo(AccountStatus status, Integer subscriptionId);

	AccountInfo insertAccountInfo(AccountStatus status, Integer subscriptionId);

}
