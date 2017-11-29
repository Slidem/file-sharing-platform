package com.file.sharing.core.actions.directory;

import java.util.Optional;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public final class CreateDirectoryAction extends AbstractItemAction {

	private final Integer parentId;

	protected CreateDirectoryAction(CreateDirectoryActionBuilder builder) {
		super(builder);
		this.parentId = builder.parentId;
	}

	/**
	 * @return the parentId. If not present, the directory is created under the
	 *         user's root.
	 */
	public Optional<Integer> getParentId() {
		return Optional.ofNullable(parentId);
	}

	public static class CreateDirectoryActionBuilder
			extends AbstractBuilder<CreateDirectoryAction, CreateDirectoryActionBuilder> {

		private Integer parentId;

		public CreateDirectoryActionBuilder withParentId(Integer parentId) {
			this.parentId = parentId;
			return getThis();
		}

		@Override
		public CreateDirectoryAction build() {
			return new CreateDirectoryAction(this);
		}

	}

	@Override
	public String toString() {
		return "CreateDirectoryAction [getUserId()=" + getUserId() + ", getItemName()=" + getItemName() + ", getPath()="
				+ getPath() + "]";
	}

}
