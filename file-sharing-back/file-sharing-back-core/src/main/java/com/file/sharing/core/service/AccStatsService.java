package com.file.sharing.core.service;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.core.entity.AccStats;

public interface AccStatsService {

	public AccStats getAccStats(AccountStatus accountStatus);

}
