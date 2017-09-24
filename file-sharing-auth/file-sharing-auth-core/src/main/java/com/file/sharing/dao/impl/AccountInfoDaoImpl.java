package com.file.sharing.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.file.sharing.dao.AccountInfoDao;
import com.file.sharing.dao.DbConstants;
import com.file.sharing.entities.AccountInfo;
import com.file.sharing.objects.AccountStatus;
import com.file.sharing.objects.AccountType;

@Repository
public class AccountInfoDaoImpl implements AccountInfoDao {

	private static final String FIND_SQL = "SELECT a.id, a.type, a.status from %s a where a.type = ? and a.status = ?";

	private static final String INSERT_SQL = "INSERT INTO %s (%s, %s) VALUES (?, ?)";

	private final JdbcTemplate jdbcTemplate;

	private final RowMapper<AccountInfo> accountInfoRowMapper;

	@Autowired
	public AccountInfoDaoImpl(JdbcTemplate jdbcTemplate, RowMapper<AccountInfo> accountInfoRowMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.accountInfoRowMapper = accountInfoRowMapper;
	}

	@Override
	public Optional<AccountInfo> findAccountInfo(AccountStatus status, AccountType type) {
		String query = String.format(FIND_SQL, DbConstants.AccountInfoTable.NAME);
		List<AccountInfo> result = jdbcTemplate.query(query, accountInfoRowMapper, type.toString(), status.toString());
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.get(0));
	}

	@Override
	public AccountInfo insertAccountInfo(AccountStatus status, AccountType type) {
		//@formatter:off
		String insertSql = String.format(INSERT_SQL, 
										 DbConstants.AccountInfoTable.NAME,
										 DbConstants.AccountInfoTable.STATUS_COLUMN, 
										 DbConstants.AccountInfoTable.TYPE_COLUMN);
		//@formatter:on
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement ps = con.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, status.toString());
				ps.setString(2, type.toString());
				return ps;
			}
		}, keyHolder);
		return AccountInfo.newInstance((Integer) keyHolder.getKeys().get("id"), status, type);
	}

}
