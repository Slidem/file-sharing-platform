package com.file.sharing.rest.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.file.ItemActionType;

/**
 * @author Andrei Aioanei
 * @created Mar 3, 2018
 *
 */
@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = CreateDirectoryDTO.Builder.class)
public class ItemActionInfoDTO {
	
	private BasicItemInfo basicItemInfo;
	private Instant actionTime;
	private ItemActionType itemActionType;
	
	private ItemActionInfoDTO() {}
	
	private ItemActionInfoDTO(Builder builder) {
		this.basicItemInfo = builder.basicItemInfo;
		this.actionTime = builder.actionTime;
		this.itemActionType = builder.itemActionType;
	}
	
	public BasicItemInfo getBasicItemInfo() {
		return basicItemInfo;
	}
	public Instant getActionTime() {
		return actionTime;
	}
	public ItemActionType getItemActionType() {
		return itemActionType;
	}
	
	public Builder builder() {
		return new Builder();
	}
	
	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {
		private BasicItemInfo basicItemInfo;
		private Instant actionTime;
		private ItemActionType itemActionType;
		
		public Builder withBasicItemInfo(BasicItemInfo basicItemInfo) {
			this.basicItemInfo = basicItemInfo;
			return this;
		}
		
		public Builder withActionTime(Instant actionTime) {
			this.actionTime = actionTime;
			return this;
		}
		
		public Builder withItemActionType(ItemActionType itemActionType) {
			this.itemActionType = itemActionType;
			return this;
		}
		
		public ItemActionInfoDTO build() {
			return new ItemActionInfoDTO(this);
		}
	}
}
