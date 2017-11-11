package com.file.sharing.core.dao;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.core.entity.AccStats;

public interface AccStatsDao {

	public AccStats getAccStats(AccountStatus accountStatus);
}
