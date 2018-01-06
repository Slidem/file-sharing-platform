package com.file.sharing.rest.dto.file;

import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.rest.dto.AbstractBasicItemInfoDTO;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
public class BasicFileInfoDTO extends AbstractBasicItemInfoDTO {

	private final FileCategories category;

	protected BasicFileInfoDTO(BasicFileInfoDtoBuilder builder) {
		super(builder);
		this.category = builder.category;
	}

	public static BasicFileInfoDtoBuilder newBuilder() {
		return new BasicFileInfoDtoBuilder();
	}

	public FileCategories getCategory() {
		return this.category;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 17, 2017
	 * 
	 */
	public static class BasicFileInfoDtoBuilder extends Builder<BasicFileInfoDTO, BasicFileInfoDtoBuilder> {

		private FileCategories category;

		public BasicFileInfoDtoBuilder withCategory(FileCategories category) {
			this.category = category;
			return getThis();
		}

		@Override
		public BasicFileInfoDTO build() {
			return new BasicFileInfoDTO(this);
		}
	}


}
