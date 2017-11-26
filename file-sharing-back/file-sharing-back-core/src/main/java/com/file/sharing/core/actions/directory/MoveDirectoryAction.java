package com.file.sharing.core.actions.directory;

import java.util.Objects;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class MoveDirectoryAction extends AbstractItemAction {

	private final Integer itemId;

	private final Integer newParentId;

	private final String newPath;

	protected MoveDirectoryAction(MoveDirectoryActionBuilder builder) {
		super(builder);
		this.itemId = Objects.requireNonNull(builder.itemId);
		this.newParentId = builder.newParentId;
		this.newPath = Objects.requireNonNull(builder.newPath);
	}

	public Integer getItemId() {
		return itemId;
	}

	public String getNewPath() {
		return newPath;
	}

	public Integer getNewParentId() {
		return newParentId;
	}

	public static class MoveDirectoryActionBuilder
			extends AbstractBuilder<MoveDirectoryAction, MoveDirectoryActionBuilder> {

		private Integer itemId;

		private Integer newParentId;

		private String newPath;

		public MoveDirectoryActionBuilder withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		public MoveDirectoryActionBuilder withNewParentId(Integer newParentId) {
			this.newParentId = newParentId;
			return getThis();
		}

		public MoveDirectoryActionBuilder withNewPath(String newPath) {
			this.newPath = newPath;
			return getThis();
		}

		@Override
		public MoveDirectoryAction build() {
			return new MoveDirectoryAction(this);
		}

	}

}
