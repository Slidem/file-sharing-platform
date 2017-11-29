package com.file.sharing.core.events.impl;

import org.springframework.core.ResolvableType;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.events.ItemActionEvent;
import com.file.sharing.core.objects.file.ItemActionStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class ItemActionEventImpl<T extends ItemAction> implements ItemActionEvent<T> {

	private final long duration;
	
	private final T itemAction;

	private final ItemActionStatus fileActionStatus;

	private ItemActionEventImpl(Builder<T> builder) {
		this.duration = builder.duration;
		this.itemAction = builder.itemAction;
		this.fileActionStatus = builder.fileActionStatus;
	}

	@Override
	public long eventDuration() {
		return duration;
	}

	@Override
	public T itemAction() {
		return itemAction;
	}


	@Override
	public ItemActionStatus status() {
		return fileActionStatus;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 11, 2017
	 */
	public static class Builder<T extends ItemAction> {

		private long duration;

		private T itemAction;
		
		private ItemActionStatus fileActionStatus;

		public Builder<T> withDuration(long duration) {
			this.duration = duration;
			return this;
		}

		public Builder<T> withItemAction(T itemAction) {
			this.itemAction = itemAction;
			return this;
		}

		public Builder<T> withFileActionStatus(ItemActionStatus fileActionStatus) {
			this.fileActionStatus = fileActionStatus;
			return this;
		}

		public ItemActionEventImpl<T> build() {
			return new ItemActionEventImpl<>(this);
		}
	}

	@Override
	public ResolvableType getResolvableType() {
		return ResolvableType.forClassWithGenerics(getClass(), ResolvableType.forInstance(itemAction));
	}



}
