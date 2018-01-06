package com.file.sharing.jms.commons.object;

import java.time.Instant;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public class JmsItemActionInfo {

	private final int userId;

	private final String itemName;

	private final String itemPath;

	private final String itemAction;

	private final String status;

	private final Instant actionTime;

	private JmsItemActionInfo(Builder builder) {
		this.userId = builder.userId;
		this.itemName = builder.itemName;
		this.itemAction = builder.itemAction;
		this.status = builder.status;
		this.itemPath = builder.itemPath;
		this.actionTime = builder.actionTime;
	}

	public static final class Builder {
		private int userId;

		private String itemName;

		private String itemPath;

		private String itemAction;

		private String status;

		private Instant actionTime;

		public Builder withUserId(int userId) {
			this.userId = userId;
			return this;
		}

		public Builder withItemName(String itemName) {
			this.itemName = itemName;
			return this;
		}

		public Builder withItemAction(String itemAction) {
			this.itemAction = itemAction;
			return this;
		}

		public Builder withStatus(String status) {
			this.status = status;
			return this;
		}

		public Builder withItemPath(String itemPath) {
			this.itemPath = itemPath;
			return this;
		}

		public Builder withActionTime(Instant actionTime) {
			this.actionTime = actionTime;
			return this;
		}

		public JmsItemActionInfo build() {
			return new JmsItemActionInfo(this);
		}

	}

	public int getUserId() {
		return userId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getItemAction() {
		return itemAction;
	}

	public String getStatus() {
		return status;
	}

	public Instant getActionTime() {
		return actionTime;
	}

	public String getItemPath() {
		return itemPath;
	}

	@Override
	public String toString() {
		return "JmsItemActionInfo [userId=" + userId + ", itemName=" + itemName + ", itemPath=" + itemPath
				+ ", itemAction=" + itemAction + ", status=" + status + ", actionTime=" + actionTime + "]";
	}


}
