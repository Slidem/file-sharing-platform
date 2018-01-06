package com.file.sharing.rest.dto;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

/**
 * @author Alexandru Mihai
 * @created Dec 30, 2017
 * 
 */
@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = CreateDirectoryDTO.Builder.class)
public class CreateDirectoryDTO {

	private String directoryName;

	private Integer parentId;

	private CreateDirectoryDTO(Builder builder) {
		this.directoryName = builder.directoryName;
		this.parentId = builder.parentId;
	}

	public String getDirectoryName() {
		return directoryName;
	}

	public Integer getParentId() {
		return parentId;
	}

	public static Builder newBuilder() {
		return new Builder();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {

		private String directoryName;

		private Integer parentId;

		public Builder withDirectoryName(String directoryName) {
			this.directoryName = Objects.requireNonNull(directoryName);
			return this;
		}

		public Builder withParentId(Integer parentId) {
			this.parentId = parentId;
			return this;
		}

		public CreateDirectoryDTO build() {
			return new CreateDirectoryDTO(this);
		}
	}

}
