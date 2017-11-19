package com.file.sharing.core.actions;

import java.util.Objects;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public abstract class AbstractItemAction implements ItemAction {

	private final Integer userId;

	private final String itemName;

	private final String path;

	protected AbstractItemAction(AbstractBuilder<?, ?> builder) {
		this.userId = Objects.requireNonNull(builder.userId);
		this.itemName = Objects.requireNonNull(builder.itemName);
		this.path = Objects.requireNonNull(builder.path);
	}

	public Integer getUserId() {
		return userId;
	}

	@Override
	public String getItemName() {
		return itemName;
	}

	@Override
	public String getPath() {
		return path;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 11, 2017
	 */
	protected abstract static class AbstractBuilder<T extends AbstractItemAction, B extends AbstractBuilder<?, ?>> {

		private Integer userId;

		private String itemName;

		private String path;

		public B withUserId(Integer userId) {
			this.userId = userId;
			return getThis();
		}

		public B withItemName(String itemName) {
			this.itemName = itemName;
			return getThis();
		}

		public B withPath(String path) {
			this.path = path;
			return getThis();
		}


		@SuppressWarnings("unchecked")
		protected B getThis() {
			return (B) this;
		}

		protected abstract T build();

	}

}
