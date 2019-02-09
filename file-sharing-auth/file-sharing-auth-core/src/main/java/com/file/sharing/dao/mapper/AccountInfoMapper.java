package com.file.sharing.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.file.sharing.dao.DbConstants;
import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

@Component
public class AccountInfoMapper implements RowMapper<AccountInfo> {

	@Override
	public AccountInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		Integer id = rs.getInt(DbConstants.AccountInfoTable.ID_COLUMN);
		String status = rs.getString(DbConstants.AccountInfoTable.STATUS_COLUMN);
		Integer subscriptionId = rs.getInt(DbConstants.AccountInfoTable.SUBSCRIPTION_COLUMN);

		return AccountInfo.newInstance(id, AccountStatus.valueOf(status), subscriptionId);
	}

}
