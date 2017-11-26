package com.file.sharing.core.actions.directory;

import java.util.Objects;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public final class DeleteDirectoryAction extends AbstractItemAction {

	private final Integer itemId;

	protected DeleteDirectoryAction(DeleteDirectoryActionBuilder builder) {
		super(builder);
		this.itemId = Objects.requireNonNull(builder.itemId);
	}

	public Integer getItemId() {
		return itemId;
	}

	public static class DeleteDirectoryActionBuilder
			extends AbstractBuilder<DeleteDirectoryAction, DeleteDirectoryActionBuilder> {

		private Integer itemId;

		public DeleteDirectoryActionBuilder withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		@Override
		public DeleteDirectoryAction build() {
			return new DeleteDirectoryAction(this);
		}
	}

	@Override
	public String toString() {
		return "DeleteDirectoryAction [itemId=" + itemId + ", getUserId()=" + getUserId() + ", getItemName()="
				+ getItemName() + ", getPath()=" + getPath() + "]";
	}

}
