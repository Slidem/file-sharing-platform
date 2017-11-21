package com.file.sharing.core.actions.directory;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class DeleteDirectoryAction extends AbstractItemAction {

	protected DeleteDirectoryAction(DeleteDirectoryActionBuilder builder) {
		super(builder);
	}

	public static class DeleteDirectoryActionBuilder
			extends AbstractBuilder<DeleteDirectoryAction, DeleteDirectoryActionBuilder> {

		@Override
		public DeleteDirectoryAction build() {
			return new DeleteDirectoryAction(this);
		}
	}

}
