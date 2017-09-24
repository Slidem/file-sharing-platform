package com.file.sharing.dao.impl;

import static com.file.sharing.dao.DbConstants.UserTable.ACCOUNT_STATUS_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.COLUMN_USER_MAP;
import static com.file.sharing.dao.DbConstants.UserTable.EMAIL_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.NAME;
import static com.file.sharing.dao.DbConstants.UserTable.NAME_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.PASSWORD_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.PICTURE_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.ROLE_COLUMN;
import static com.file.sharing.dao.DbConstants.UserTable.SURNAME_COLUMN;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.file.sharing.dao.DbConstants;
import com.file.sharing.dao.UserDAO;
import com.file.sharing.dao.mapper.BaseUserMapper;
import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.entities.impl.User;

@Repository
public class UserDaoImpl implements UserDAO {

	private static final String SELECT_BASE_USER_SQL_FORMAT;

	private static final String INSERT_USER_SQL;

	static {
		SELECT_BASE_USER_SQL_FORMAT = "SELECT u.id, u.email, u.id_role, u.password from %s as u WHERE u.%s = ?";
		INSERT_USER_SQL = "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s) values (?, ?, ?, ?, ?, ?, ?)";
	}

	private final JdbcTemplate jdbcTemplate;

	private final BaseUserMapper baseClientMapper;

	@Autowired
	public UserDaoImpl(JdbcTemplate jdbcTemplate, BaseUserMapper baseClientMapper) {
		this.jdbcTemplate = jdbcTemplate;
		this.baseClientMapper = baseClientMapper;
	}

	@Override
	public Optional<BaseUser> getBaseUserById(Integer id) {
		return getBaseUserByCriteria(DbConstants.UserTable.ID_COLUMN, id);
	}

	@Override
	public Optional<BaseUser> getBaseUserByMail(String email) {
		return getBaseUserByCriteria(DbConstants.UserTable.EMAIL_COLUMN, email);
	}

	@Override
	public User createUser(User user) {
		//@formatter:off
		Object[] params = { COLUMN_USER_MAP.get(NAME_COLUMN).apply(user), 
							COLUMN_USER_MAP.get(SURNAME_COLUMN).apply(user),
							COLUMN_USER_MAP.get(PASSWORD_COLUMN).apply(user), 
							COLUMN_USER_MAP.get(ROLE_COLUMN).apply(user),
							COLUMN_USER_MAP.get(ACCOUNT_STATUS_COLUMN).apply(user),
							COLUMN_USER_MAP.get(PICTURE_COLUMN).apply(user), 
							COLUMN_USER_MAP.get(EMAIL_COLUMN).apply(user) 
						};
		String insertSql = String.format(INSERT_USER_SQL, 
										 NAME, 
										 NAME_COLUMN, 
										 SURNAME_COLUMN, 
										 PASSWORD_COLUMN,
										 ROLE_COLUMN, 
										 ACCOUNT_STATUS_COLUMN, 
										 PICTURE_COLUMN, 
										 EMAIL_COLUMN);
		//@formatter:on

		jdbcTemplate.update(insertSql, params);

		return user;
	}

	// ---------------------------------------------------------------------------

	private Optional<BaseUser> getBaseUserByCriteria(String column, Object value) {
		String query = String.format(SELECT_BASE_USER_SQL_FORMAT, NAME, column);
		List<BaseUser> result = jdbcTemplate.query(query, baseClientMapper, value);
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.get(0));
	}

}
