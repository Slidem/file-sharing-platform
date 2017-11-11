package com.file.sharing.core.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.core.dao.AccStatsDao;
import com.file.sharing.core.entity.AccStats;
import com.file.sharing.core.service.AccStatsService;

@Service
@Transactional
public class AccStatsServiceImpl implements AccStatsService {

	private AccStatsDao accStatsDao;

	@Autowired
	public AccStatsServiceImpl(AccStatsDao accStatsDao) {
		this.accStatsDao = accStatsDao;
	}

	@Override
	public AccStats getAccStats(AccountStatus accountStatus) {
		return accStatsDao.getAccStats(accountStatus);
	}

}
