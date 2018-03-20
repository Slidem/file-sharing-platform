package com.file.sharing.core.objects.impl;

import java.time.Instant;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.core.objects.file.ItemActionType;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = ItemActionInfoImpl.Builder.class)
public class ItemActionInfoImpl implements ItemActionInfo {

	private BasicItemInfo basicItemInfo;

	private Instant actionTime;

	private ItemActionType actionType;

	/**
	 * @param basicItemInfo
	 * @param actionTime
	 * @param actionType
	 */
	private ItemActionInfoImpl() {
	}

	@Override
	public Optional<BasicItemInfo> getBasicItemInfo() {
		return Optional.ofNullable(basicItemInfo);
	}

	@Override
	public Instant getActionTime() {
		return actionTime;
	}

	@Override
	public ItemActionType getActionType() {
		return actionType;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {

		private BasicItemInfo basicItemInfo;

		private Instant actionTime;

		private ItemActionType actionType;

		public Builder withBasicItemInfo(BasicItemInfo basicItemInfo) {
			this.basicItemInfo = basicItemInfo;
			return this;
		}

		public Builder withActionTime(Instant actionTime) {
			this.actionTime = actionTime;
			return this;
		}

		public Builder withActionType(ItemActionType actionType) {
			this.actionType = actionType;
			return this;
		}

		public ItemActionInfo build() {
			ItemActionInfoImpl itemActionInfo = new ItemActionInfoImpl();
			itemActionInfo.actionTime = Objects.requireNonNull(this.actionTime);
			itemActionInfo.actionType = Objects.requireNonNull(this.actionType);
			itemActionInfo.basicItemInfo = this.basicItemInfo;
			return itemActionInfo;
		}

	}

}
