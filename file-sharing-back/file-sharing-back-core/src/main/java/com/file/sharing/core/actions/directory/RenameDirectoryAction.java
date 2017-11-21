package com.file.sharing.core.actions.directory;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class RenameDirectoryAction extends AbstractItemAction {

	protected RenameDirectoryAction(Builder builder) {
		super(builder);
	}

	public static class Builder extends AbstractBuilder<RenameDirectoryAction, Builder> {
		@Override
		protected RenameDirectoryAction build() {
			return new RenameDirectoryAction(this);
		}
	}

}
