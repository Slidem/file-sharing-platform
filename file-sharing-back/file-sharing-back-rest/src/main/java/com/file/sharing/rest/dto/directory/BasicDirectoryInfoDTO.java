package com.file.sharing.rest.dto.directory;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.file.sharing.rest.dto.AbstractBasicItemInfoDTO;

/**
 * @author Alexandru Mihai
 * @created Dec 17, 2017
 * 
 */
@JsonDeserialize(builder = BasicDirectoryInfoDTO.BasicDirectoryInfoDtoBuilder.class)
public class BasicDirectoryInfoDTO extends AbstractBasicItemInfoDTO {

	protected BasicDirectoryInfoDTO(BasicDirectoryInfoDtoBuilder builder) {
		super(builder);
	}

	public static BasicDirectoryInfoDtoBuilder newBuilder() {
		return new BasicDirectoryInfoDtoBuilder();
	}

	/**
	 * @author Alexandru Mihai
	 * @created Dec 17, 2017
	 * 
	 */
	@JsonPOJOBuilder(withPrefix = "with", buildMethodName = "build")
	public static class BasicDirectoryInfoDtoBuilder extends Builder<BasicDirectoryInfoDTO, BasicDirectoryInfoDtoBuilder> {
		@Override
		public BasicDirectoryInfoDTO build() {
			return new BasicDirectoryInfoDTO(this);
		}
	}


}
