package com.file.sharing.core.actions.directory;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public class MoveDirectoryAction extends AbstractItemAction {

	protected MoveDirectoryAction(Builder builder) {
		super(builder);
	}

	public static class Builder extends AbstractBuilder<MoveDirectoryAction, Builder> {
		@Override
		protected MoveDirectoryAction build() {
			return new MoveDirectoryAction(this);
		}

	}

}
