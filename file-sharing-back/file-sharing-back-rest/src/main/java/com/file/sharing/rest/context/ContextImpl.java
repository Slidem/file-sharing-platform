package com.file.sharing.rest.context;

import com.file.sharing.common.user.AccountType;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.StorageInfo;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
public final class ContextImpl implements Context {

	private final String userEmail;

	private final Integer getUserId;

	private final AccountType userAccountType;

	private final StorageInfo userStorageInfo;

	private ContextImpl(ContextBuidler builder) {
		this.userEmail = builder.userEmail;
		this.getUserId = builder.userId;
		this.userAccountType = builder.userAccountType;
		this.userStorageInfo = builder.userStorageInfo;
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
	public StorageInfo getUserStorageInfo() {
		return userStorageInfo;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Oct 29, 2017
	 */
	public static class ContextBuidler {

		private String userEmail;

		private Integer userId;

		private AccountType userAccountType;

		private StorageInfo userStorageInfo;

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

		public ContextBuidler setUserStorageLocation(StorageInfo userStorageInfo) {
			this.userStorageInfo = userStorageInfo;
			return this;
		}

		public ContextImpl build() {
			return new ContextImpl(this);
		}

	}

}
