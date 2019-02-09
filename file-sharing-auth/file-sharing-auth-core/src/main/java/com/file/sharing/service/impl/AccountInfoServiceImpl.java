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
	public AccountInfo create(AccountStatus status, Integer subscriptionId) {
		return accountInfoDao.insertAccountInfo(status, subscriptionId);
	}

	@Override
	@Transactional
	public Optional<AccountInfo> find(AccountStatus status, Integer subscriptionId) {
		return accountInfoDao.findAccountInfo(status, subscriptionId);
	}

	@Override
	@Transactional(propagation = Propagation.NEVER)
	public AccountInfo createOrGet(AccountStatus status, Integer subscriptionId) {
		try {
			return create(status, subscriptionId);
		} catch (DataIntegrityViolationException e) {
			return find(status, subscriptionId).orElse(null);
		}
	}

}
