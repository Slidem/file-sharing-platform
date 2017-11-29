package com.file.sharing.core.actions.directory;

import static java.util.Objects.requireNonNull;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class RenameDirectoryAction extends AbstractItemAction {

	private final String newItemName;

	private final Integer itemId;

	protected RenameDirectoryAction(RenameDirectoryActionBuilder builder) {
		super(builder);
		this.newItemName = requireNonNull(builder.newItemName);
		this.itemId = requireNonNull(builder.itemId);
	}

	public String getNewItemName() {
		return newItemName;
	}


	public Integer getItemId() {
		return itemId;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 26, 2017
	 */
	public static class RenameDirectoryActionBuilder
			extends AbstractBuilder<RenameDirectoryAction, RenameDirectoryActionBuilder> {

		private String newItemName;

		private Integer itemId;

		public RenameDirectoryActionBuilder withNewItemName(String newItemName) {
			this.newItemName = newItemName;
			return getThis();
		}

		public RenameDirectoryActionBuilder withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		@Override
		public RenameDirectoryAction build() {
			return new RenameDirectoryAction(this);
		}
	}

}
