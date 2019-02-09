package com.file.sharing.core.objects;

import com.file.sharing.common.user.AccountStatus;
import com.file.sharing.core.entity.Subscription;

/**
 * @author Alexandru
 *
 */
public class AccStatsInfo {

	private final Subscription subscription;

	private final AccountStatus status;

	/**
	 * @param subscription
	 * @param status
	 */
	public AccStatsInfo(Subscription subscription, AccountStatus status) {
		this.subscription = subscription;
		this.status = status;
	}

	public Subscription getSubscription() {
		return subscription;
	}

	public AccountStatus getStatus() {
		return status;
	}

}
