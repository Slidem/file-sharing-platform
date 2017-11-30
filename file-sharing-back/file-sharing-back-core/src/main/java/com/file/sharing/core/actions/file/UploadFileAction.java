package com.file.sharing.core.actions.file;

import java.util.Arrays;

import com.file.sharing.core.actions.AbstractItemAction;

/**
 * @author Alexandru Mihai
 * @created Nov 30, 2017
 * 
 */
public class UploadFileAction extends AbstractItemAction {

	private final byte[] bytes;

	protected UploadFileAction(UploadFileActionBuilder builder) {
		super(builder);
		this.bytes = builder.bytes;
	}

	public byte[] getBytes() {
		return bytes;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 30, 2017
	 * 
	 */
	public static class UploadFileActionBuilder extends AbstractBuilder<UploadFileAction, UploadFileActionBuilder> {

		private byte[] bytes;

		@Override
		public UploadFileAction build() {
			return new UploadFileAction(this);
		}

		public UploadFileActionBuilder withBytes(byte[] bytes) {
			this.bytes = Arrays.copyOf(bytes, bytes.length);
			return getThis();
		}

	}

}
