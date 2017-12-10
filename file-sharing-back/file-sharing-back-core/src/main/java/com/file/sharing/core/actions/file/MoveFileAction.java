package com.file.sharing.core.actions.file;

import java.util.Objects;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class MoveFileAction extends AbstractItemAction {

	private final Integer itemId;

	private final Integer newParentId;

	private final String newPath;

	protected MoveFileAction(MoveFileActionBuilder builder) {
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

	public static class MoveFileActionBuilder
			extends AbstractBuilder<MoveFileAction, MoveFileActionBuilder> {

		private Integer itemId;

		private Integer newParentId;

		private String newPath;

		public MoveFileActionBuilder withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		public MoveFileActionBuilder withNewParentId(Integer newParentId) {
			this.newParentId = newParentId;
			return getThis();
		}

		public MoveFileActionBuilder withNewPath(String newPath) {
			this.newPath = newPath;
			return getThis();
		}

		@Override
		public MoveFileAction build() {
			return new MoveFileAction(this);
		}

	}

}
