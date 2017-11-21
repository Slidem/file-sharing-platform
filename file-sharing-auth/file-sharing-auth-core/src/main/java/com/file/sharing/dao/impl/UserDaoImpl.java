package com.file.sharing.dao.impl;

import static com.file.sharing.dao.DbConstants.UserTable.ACCOUNT_STATUS_COLUMN;
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
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import com.file.sharing.dao.DbConstants;
import com.file.sharing.dao.UserDAO;
import com.file.sharing.dao.mapper.BaseUserMapper;
import com.file.sharing.entities.impl.BaseUser;
import com.file.sharing.entities.impl.User;

/**
 * @author Alexandru Mihai
 * @created Nov 17, 2017
 */
@Repository
public class UserDaoImpl implements UserDAO {

	private static final String SELECT_BASE_USER_SQL_FORMAT;

	private static final String INSERT_USER_SQL;

	static {
		SELECT_BASE_USER_SQL_FORMAT = "SELECT u.id, u.email, u.role_id, u.password from %s as u WHERE u.%s = :%s";
		INSERT_USER_SQL = "INSERT INTO %1$s (%2$s, %3$s, %4$s, %5$s, %6$s, %7$s, %8$s) values (:%2$s, :%3$s, :%4$s, :%5$s, :%6$s, :%7$s, :%8$s)";
	}

	private final NamedParameterJdbcTemplate namedJdbcTemplate;

	private final BaseUserMapper baseClientMapper;

	@Autowired
	public UserDaoImpl(NamedParameterJdbcTemplate namedJdbcTemplate, BaseUserMapper baseClientMapper) {
		this.namedJdbcTemplate = namedJdbcTemplate;
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
	public Integer createUser(User user) {

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue(NAME_COLUMN, user.getName());
		paramSource.addValue(SURNAME_COLUMN, user.getSurname());
		paramSource.addValue(PASSWORD_COLUMN, user.getPassword());
		paramSource.addValue(ROLE_COLUMN, user.getRole().getId());
		paramSource.addValue(ACCOUNT_STATUS_COLUMN, user.getAccountInfo().getId());
		paramSource.addValue(PICTURE_COLUMN, user.getPicture());
		paramSource.addValue(EMAIL_COLUMN, user.getEmail());

		String insertSql = String.format(INSERT_USER_SQL, NAME, NAME_COLUMN, SURNAME_COLUMN, PASSWORD_COLUMN,
				ROLE_COLUMN, ACCOUNT_STATUS_COLUMN, PICTURE_COLUMN, EMAIL_COLUMN);
		// @formatter:on

		GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

		namedJdbcTemplate.update(insertSql, paramSource, keyHolder);

		return (Integer) keyHolder.getKeys().get("id");
	}

	// ---------------------------------------------------------------------------


	private Optional<BaseUser> getBaseUserByCriteria(String column, Object value) {
		String query = String.format(SELECT_BASE_USER_SQL_FORMAT, NAME, column, column);

		MapSqlParameterSource paramSource = new MapSqlParameterSource();

		paramSource.addValue(column, value);

		List<BaseUser> result = namedJdbcTemplate.query(query, paramSource, baseClientMapper);
		if (result.isEmpty()) {
			return Optional.empty();
		}
		return Optional.of(result.get(0));
	}

}
