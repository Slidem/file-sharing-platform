package com.file.sharing.rest.context;

import com.file.sharing.core.entity.Subscription;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.objects.StorageInfo;

import java.util.Collections;
import java.util.Map;

/**
 * @author Alexandru Mihai
 * @created Oct 29, 2017
 */
public final class ContextImpl implements Context {

	private final String userEmail;

	private final Integer getUserId;

	private final Subscription userSubscription;

	private final StorageInfo userStorageInfo;

	private ContextImpl(ContextBuidler builder) {
		this.userEmail = builder.userEmail;
		this.getUserId = builder.userId;
		this.userSubscription = builder.userSubscription;
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
	public Subscription getUserSubscription() {
		return userSubscription;
	}

	@Override
	public StorageInfo getUserStorageInfo() {
		return userStorageInfo;
	}

	@Override
	public Map<Long, Long> getUserUsedAndTotalSpace() {
		return Collections.singletonMap(userStorageInfo.getUsedSize(), userSubscription.getStorageSize());
	}

	/**
	 * @author Alexandru Mihai
	 * @created Oct 29, 2017
	 */
	public static class ContextBuidler {

		private String userEmail;

		private Integer userId;

		private Subscription userSubscription;

		private StorageInfo userStorageInfo;

		public ContextBuidler setUserEmail(String userEmail) {
			this.userEmail = userEmail;
			return this;
		}

		public ContextBuidler setUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public ContextBuidler setUserSubscription(Subscription userSubscription) {
			this.userSubscription = userSubscription;
			return this;
		}

		public ContextBuidler setUserStorageInfo(StorageInfo userStorageInfo) {
			this.userStorageInfo = userStorageInfo;
			return this;
		}

		public ContextImpl build() {
			return new ContextImpl(this);
		}

	}

}
