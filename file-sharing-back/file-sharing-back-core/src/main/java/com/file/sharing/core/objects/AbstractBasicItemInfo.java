package com.file.sharing.core.objects;

import com.file.sharing.core.objects.file.ItemType;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
public abstract class AbstractBasicItemInfo implements BasicItemInfo {

	private final Integer id;

	private final String name;

	private final ItemType itemType;

	protected AbstractBasicItemInfo(Builder<?, ?> builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.itemType = builder.itemType;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public ItemType getItemType() {
		return this.itemType;
	}

	@Override
	public String getName() {
		return this.name;
	}

	protected abstract static class Builder<T extends AbstractBasicItemInfo, B extends Builder<T, B>> {

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
