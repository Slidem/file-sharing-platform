package com.file.sharing.core.objects.directory;

import com.file.sharing.core.objects.AbstractBasicItemInfo;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
public class BasicDirectoryInfoImpl extends AbstractBasicItemInfo implements BasicDirectoryInfo {

	protected BasicDirectoryInfoImpl(BasicDirectoryInfoBuilder builder) {
		super(builder);
	}

	public static BasicDirectoryInfoBuilder newBuilder() {
		return new BasicDirectoryInfoBuilder();
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 17, 2017
	 * 
	 */
	public static class BasicDirectoryInfoBuilder extends Builder<BasicDirectoryInfoImpl, BasicDirectoryInfoBuilder> {
		@Override
		public BasicDirectoryInfoImpl build() {
			return new BasicDirectoryInfoImpl(this);
		}
	}


}
