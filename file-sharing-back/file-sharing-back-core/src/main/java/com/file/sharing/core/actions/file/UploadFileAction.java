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

	private final Integer parentId;

	protected UploadFileAction(UploadFileActionBuilder builder) {
		super(builder);
		this.bytes = builder.bytes;
		this.parentId = builder.parentId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public Integer getParentId() {
		return parentId;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 30, 2017
	 * 
	 */
	public static class UploadFileActionBuilder extends AbstractBuilder<UploadFileAction, UploadFileActionBuilder> {

		private byte[] bytes;

		private Integer parentId;

		public UploadFileActionBuilder withBytes(byte[] bytes) {
			this.bytes = Arrays.copyOf(bytes, bytes.length);
			return getThis();
		}

		public UploadFileActionBuilder withParentId(Integer parentId) {
			this.parentId = parentId;
			return getThis();
		}

		@Override
		public UploadFileAction build() {
			return new UploadFileAction(this);
		}

	}

}
