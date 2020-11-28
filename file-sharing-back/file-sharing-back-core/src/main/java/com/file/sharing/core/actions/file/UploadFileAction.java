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

	private final Long size;

	protected UploadFileAction(UploadFileActionBuilder builder) {
		super(builder);
		this.bytes = builder.bytes;
		this.parentId = builder.parentId;
		this.size = builder.size;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public Integer getParentId() {
		return parentId;
	}

	public Long getSize() {
		return size;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 30, 2017
	 *
	 */
	public static class UploadFileActionBuilder extends AbstractBuilder<UploadFileAction, UploadFileActionBuilder> {

		private byte[] bytes;

		private Integer parentId;

		private Long size;

		public UploadFileActionBuilder withBytes(byte[] bytes) {
			this.bytes = Arrays.copyOf(bytes, bytes.length);
			return getThis();
		}

		public UploadFileActionBuilder withParentId(Integer parentId) {
			this.parentId = parentId;
			return getThis();
		}

		public UploadFileActionBuilder withSize(Long size) {
			this.size = size;
			return getThis();
		}

		@Override
		public UploadFileAction build() {
			return new UploadFileAction(this);
		}

	}

}
