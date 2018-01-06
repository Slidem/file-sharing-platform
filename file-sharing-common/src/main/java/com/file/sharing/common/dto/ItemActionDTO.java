package com.file.sharing.common.dto;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
public class ItemActionDTO {

	private Integer userId;

	private String itemName;

	private String path;

	private Instant actionTime;

	private ItemActionDTO() {
	}

	public Integer getUserId() {
		return userId;
	}

	public String getItemName() {
		return itemName;
	}

	public String getPath() {
		return path;
	}

	public Instant getActionTime() {
		return actionTime;
	}

	public static class Builder {

		private Integer userId;

		private String itemName;

		private String path;

		private Instant actionTime;

		public Builder withUserId(Integer userId) {
			this.userId = userId;
			return this;
		}

		public Builder withItemName(String itemName) {
			this.itemName = itemName;
			return this;
		}

		public Builder withPath(String path) {
			this.path = path;
			return this;
		}

		public Builder withActionTime(Instant actionTime) {
			this.actionTime = actionTime;
			return this;
		}

		public ItemActionDTO build() {
			ItemActionDTO itemActionDTO = new ItemActionDTO();

			itemActionDTO.itemName = Objects.requireNonNull(this.itemName);
			itemActionDTO.path = Objects.requireNonNull(this.path);
			itemActionDTO.userId = Objects.requireNonNull(this.userId);
			itemActionDTO.actionTime = Objects.requireNonNull(this.actionTime);

			return itemActionDTO;
		}
	}

}
