package com.file.sharing.core.actions.file;

import java.util.Objects;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Dec 10, 2017
 * 
 */
public class DeleteFileAction extends AbstractItemAction {

	private final Integer itemId;

	protected DeleteFileAction(DeleteFileActionBuilder builder) {
		super(builder);
		this.itemId = Objects.requireNonNull(builder.itemId);
	}

	public Integer getItemId() {
		return itemId;
	}
	
	public static final class DeleteFileActionBuilder extends AbstractBuilder<DeleteFileAction, DeleteFileActionBuilder>{

		private Integer itemId;

		public DeleteFileActionBuilder withItemId(Integer itemId) {
			this.itemId = itemId;
			return getThis();
		}

		@Override
		public DeleteFileAction build() {
			return new DeleteFileAction(this);
		}
		
	}

}
