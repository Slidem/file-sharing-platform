package com.file.sharing.rest.context;

import com.file.sharing.common.user.AccountType;
import com.file.sharing.core.objects.Context;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
public final class ContextImpl implements Context {

	private final String userEmail;

	private final Integer getUserId;

	private final AccountType userAccountType;

	private final String userStorageLocation;

	private ContextImpl(ContextBuidler builder) {
		this.userEmail = builder.userEmail;
		this.getUserId = builder.userId;
		this.userAccountType = builder.userAccountType;
		this.userStorageLocation = builder.userStorageLocation;
	}

	@Override
	public String getUserEmail() {
		return userEmail;
	}

	@Override
	public Integer getGetUserId() {
		return getUserId;
	}

	@Override
	public AccountType getUserAccountType() {
		return userAccountType;
	}

	@Override
	public String getUserStorageLocation() {
		return userStorageLocation;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Oct 29, 2017
	 */
	public static class ContextBuidler {

		private String userEmail;

		private Integer userId;

		private AccountType userAccountType;

		private String userStorageLocation;

		public ContextBuidler setUserEmail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}

		public ContextBuidler setUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public ContextBuidler setUserAccountType(AccountType userAccountType) {
			this.userAccountType = userAccountType;
			return this;
		}

		public ContextBuidler setUserStorageLocation(String userStorageLocation) {
			this.userStorageLocation = userStorageLocation;
			return this;
		}

		public ContextImpl build() {
			return new ContextImpl(this);
		}

	}

}
