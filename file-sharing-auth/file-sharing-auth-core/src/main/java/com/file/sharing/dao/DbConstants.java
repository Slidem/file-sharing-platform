package com.file.sharing.dao;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;

import com.file.sharing.entities.impl.User;

public final class DbConstants {

	public static final String SCHEMA_NAME = "public";

	public static final class AccountInfoTable {

		public static final String NAME = SCHEMA_NAME + ".acc_stats";

		public static final String ID_COLUMN = "id";

		public static final String TYPE_COLUMN = "type";

		public static final String STATUS_COLUMN = "status";

		private AccountInfoTable() {
		}

	}

	public static final class UserTable {

		public static final String NAME = SCHEMA_NAME + ".user";

		public static final String ID_COLUMN = "id";

		public static final String EMAIL_COLUMN = "email";

		public static final String ROLE_COLUMN = "role_id";

		public static final String NAME_COLUMN = "name";

		public static final String SURNAME_COLUMN = "surname";

		public static final String ACCOUNT_STATUS_COLUMN = "accstats_id";

		public static final String PASSWORD_COLUMN = "password";

		public static final String PICTURE_COLUMN = "picture";

		public static final Map<String, Function<User, Object>> COLUMN_USER_MAP = new LinkedHashMap<>();

		static {
			COLUMN_USER_MAP.put(ID_COLUMN, u -> u.getId());
			COLUMN_USER_MAP.put(EMAIL_COLUMN, u -> u.getEmail());
			COLUMN_USER_MAP.put(ROLE_COLUMN, u -> u.getRole().getId());
			COLUMN_USER_MAP.put(NAME_COLUMN, u -> u.getName());
			COLUMN_USER_MAP.put(SURNAME_COLUMN, u -> u.getSurname());
			COLUMN_USER_MAP.put(ACCOUNT_STATUS_COLUMN, u -> u.getAccountInfo().getId());
			COLUMN_USER_MAP.put(PASSWORD_COLUMN, u -> u.getPassword());
			COLUMN_USER_MAP.put(PICTURE_COLUMN, u -> u.getPicture());
		}

		private UserTable() {
		}

	}

	private DbConstants() {
	}

}
