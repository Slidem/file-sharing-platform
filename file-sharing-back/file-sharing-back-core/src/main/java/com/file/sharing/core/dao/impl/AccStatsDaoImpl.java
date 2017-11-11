package com.file.sharing.core.dao.impl;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.core.dao.AbstractDaoImpl;
import com.file.sharing.core.dao.AccStatsDao;
import com.file.sharing.core.entity.AccStats;
import com.file.sharing.core.entity.Role;

@Repository
public class AccStatsDaoImpl extends AbstractDaoImpl<Role> implements AccStatsDao {

	@Override
	public AccStats getAccStats(AccountStatus accountStatus) {
		TypedQuery<AccStats> query = entityManager.createQuery("from AccStats a where a.status=:status", AccStats.class)
				.setParameter("status", accountStatus);
		AccStats accStats = null;
		try {
			accStats = query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accStats;
	}

}
