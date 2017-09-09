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

	private User(ClientBuilder builder) {
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

	public static final class ClientBuilder extends AbstractBaseClientBuilder<User, ClientBuilder> {

		private String name;

		private String surname;

		private AccountInfo accountInfo;

		private byte[] picture;

		public ClientBuilder setName(String name) {
			this.name = name;
			return getThis();
		}

		public ClientBuilder setSurname(String surname) {
			this.surname = surname;
			return getThis();
		}

		public ClientBuilder setAccountInfo(AccountInfo accountInfo) {
			this.accountInfo = accountInfo;
			return getThis();
		}

		public ClientBuilder setPicture(byte[] picture) {
			this.picture = Arrays.clone(picture);
			return getThis();
		}

		public User build() {
			return new User(this);
		}

	}

}
