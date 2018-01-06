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
@JsonDeserialize(builder = RenameItemDTO.Builder.class)
public class RenameItemDTO {


	private String newName;

	public RenameItemDTO(Builder builder) {
		this.newName = Objects.requireNonNull(builder.newName);
	}


	public String getNewName() {
		return newName;
	}

	public Builder newBuilder() {
		return new Builder();
	}

	@JsonPOJOBuilder(buildMethodName = "build", withPrefix = "with")
	public static class Builder {

		private String newName;

		public Builder withNewName(String newName) {
			this.newName = newName;
			return this;
		}

		public RenameItemDTO build() {
			return new RenameItemDTO(this);
		}

	}

}
