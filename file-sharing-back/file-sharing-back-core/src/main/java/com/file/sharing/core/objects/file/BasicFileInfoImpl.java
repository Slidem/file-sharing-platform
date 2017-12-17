package com.file.sharing.core.objects.file;

import com.file.sharing.core.objects.AbstractBasicItemInfo;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
public class BasicFileInfoImpl extends AbstractBasicItemInfo implements BasicFileInfo {

	private final FileCategories category;

	protected BasicFileInfoImpl(BasicFileInfoBuilder builder) {
		super(builder);
		this.category = builder.category;
	}

	public static BasicFileInfoBuilder newBuilder() {
		return new BasicFileInfoBuilder();
	}

	@Override
	public FileCategories getCategory() {
		return this.category;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 17, 2017
	 * 
	 */
	public static class BasicFileInfoBuilder extends Builder<BasicFileInfoImpl, BasicFileInfoBuilder> {

		private FileCategories category;

		public BasicFileInfoBuilder withCategory(FileCategories category) {
			this.category = category;
			return getThis();
		}

		@Override
		public BasicFileInfoImpl build() {
			return new BasicFileInfoImpl(this);
		}
	}


}
