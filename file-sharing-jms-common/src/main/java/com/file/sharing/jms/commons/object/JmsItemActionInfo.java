package com.file.sharing.jms.commons.object;

/**
 * @author Alexandru Mihai
 * @created Nov 18, 2017
 */
public class JmsItemActionInfo {

	private final int userId;

	private final String itemName;

	private final String itemAction;

	private final String status;

	private JmsItemActionInfo(Builder builder) {
		this.userId = builder.userId;
		this.itemName = builder.itemName;
		this.itemAction = builder.itemAction;
		this.status = builder.status;
	}

	public static final class Builder {
		private int userId;

		private String itemName;

		private String itemAction;

		private String status;

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

	@Override
	public String toString() {
		return "JmsItemActionInfo [userId=" + userId + ", itemName=" + itemName + ", itemAction=" + itemAction
				+ ", status=" + status + "]";
	}

}
