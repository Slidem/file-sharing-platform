package com.file.sharing.core.actions.file;

import java.util.Objects;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Dec 10, 2017
 * 
 */
public class RenameFileAction extends AbstractItemAction {

	private final Integer itemId;

	private final String newItemName;

	protected RenameFileAction(RenameFileActionBuider builder) {
		super(builder);
		this.itemId = Objects.requireNonNull(builder.itemId);
		this.newItemName = Objects.requireNonNull(builder.newItemName);
	}

	public Integer getItemId() {
		return itemId;
	}

	public String getNewItemName() {
		return newItemName;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 10, 2017
	 * 
	 */
	public static final class RenameFileActionBuider extends AbstractBuilder<RenameFileAction, RenameFileActionBuider> {

		private Integer itemId;

		private String newItemName;

		public RenameFileActionBuider withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		public RenameFileActionBuider withNewItemName(String newItemName) {
			this.newItemName = newItemName;
			return getThis();
		}

		@Override
		public RenameFileAction build() {
			return new RenameFileAction(this);
		}

	}
}
