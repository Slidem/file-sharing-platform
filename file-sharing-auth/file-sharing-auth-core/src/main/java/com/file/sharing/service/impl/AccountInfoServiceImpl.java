package com.file.sharing.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.config.Constants;
import com.file.sharing.dao.AccountInfoDao;
import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;
import com.file.sharing.service.AccountInfoService;

@Service
@Transactional(value = Constants.PG_JDBC_TX_NAME, readOnly = false)
public class AccountInfoServiceImpl implements AccountInfoService {

	private final AccountInfoDao accountInfoDao;

	@Autowired
	public AccountInfoServiceImpl(AccountInfoDao accountInfoDao) {
		this.accountInfoDao = accountInfoDao;
	}

	@Override
	@Transactional(rollbackFor = DataIntegrityViolationException.class)
	public AccountInfo create(AccountStatus status, AccountType type) {
		return accountInfoDao.insertAccountInfo(status, type);
	}

	@Override
	@Transactional
	public Optional<AccountInfo> find(AccountStatus status, AccountType type) {
		return accountInfoDao.findAccountInfo(status, type);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public AccountInfo createOrGet(AccountStatus status, AccountType type) {
		try {
			return create(status, type);
		} catch (DataIntegrityViolationException e) {
			return find(status, type).orElse(null);
		}
	}

}
