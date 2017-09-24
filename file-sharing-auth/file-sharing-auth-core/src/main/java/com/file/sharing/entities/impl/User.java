package com.file.sharing.entities.impl;

import org.bouncycastle.util.Arrays;

import com.file.sharing.entities.AccountInfo;

/**
 * @author Alexandru
 *
 */
public final class User extends BaseUser {

	private final String name;

	private final String surname;

	private final AccountInfo accountInfo;

	private final byte[] picture;

	private User(UserBuilder builder) {
		super(builder);
		this.name = builder.name;
		this.surname = builder.surname;
		this.accountInfo = builder.accountInfo;
		this.picture = builder.picture;
	}

	public String getName() {
		return this.name;
	}

	public String getSurname() {
		return this.surname;
	}

	public AccountInfo getAccountInfo() {
		return this.accountInfo;
	}

	public byte[] getPicture() {
		return this.picture;
	}

	public static final class UserBuilder extends AbstractBaseClientBuilder<User, UserBuilder> {

		private String name;

		private String surname;

		private AccountInfo accountInfo;

		private byte[] picture;

		public UserBuilder setName(String name) {
			this.name = name;
			return getThis();
		}

		public UserBuilder setSurname(String surname) {
			this.surname = surname;
			return getThis();
		}

		public UserBuilder setAccountInfo(AccountInfo accountInfo) {
			this.accountInfo = accountInfo;
			return getThis();
		}

		public UserBuilder setPicture(byte[] picture) {
			this.picture = Arrays.clone(picture);
			return getThis();
		}

		public User build() {
			return new User(this);
		}

	}

}
