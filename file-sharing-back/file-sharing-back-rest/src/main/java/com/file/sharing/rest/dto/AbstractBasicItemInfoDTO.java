package com.file.sharing.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
@JsonInclude(value = Include.NON_NULL)
public abstract class AbstractBasicItemInfoDTO {

	private final Integer id;

	private final String name;

	private final ItemType itemType;

	protected AbstractBasicItemInfoDTO(Builder<?, ?> builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.itemType = builder.itemType;
	}

	public Integer getId() {
		return this.id;
	}

	public ItemType getItemType() {
		return this.itemType;
	}

	public String getName() {
		return this.name;
	}

	public abstract static class Builder<T extends AbstractBasicItemInfoDTO, B extends Builder<T, B>> {

		private Integer id;

		private String name;

		private ItemType itemType;

		public B withId(Integer id) {
			this.id = id;
			return getThis();
		}

		public B withName(String name) {
			this.name = name;
			return getThis();
		}

		public B withItemType(ItemType itemType) {
			this.itemType = itemType;
			return getThis();
		}

		@SuppressWarnings("unchecked")
		protected B getThis() {
			return (B) this;
		}

		public abstract T build();

	}
}
