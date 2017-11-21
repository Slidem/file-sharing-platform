package com.file.sharing.core.actions.directory;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class CreateDirectoryAction extends AbstractItemAction {

	protected CreateDirectoryAction(CreateDirectoryActionBuilder builder) {
		super(builder);
	}


	public static class CreateDirectoryActionBuilder
			extends AbstractBuilder<CreateDirectoryAction, CreateDirectoryActionBuilder> {

		@Override
		public CreateDirectoryAction build() {
			return new CreateDirectoryAction(this);
		}

	}

}
