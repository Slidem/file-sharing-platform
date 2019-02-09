package com.file.sharing.objects;

/**
 * This enum must always be in sync with the subscription table
 */
public enum AccountType {
	GUEST(1), REGULAR(2), PREMIUM(3), GOLD(4);

	int subscriptionId;

	AccountType(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public int getSubscriptionId() {
		return this.subscriptionId;
	}

}
