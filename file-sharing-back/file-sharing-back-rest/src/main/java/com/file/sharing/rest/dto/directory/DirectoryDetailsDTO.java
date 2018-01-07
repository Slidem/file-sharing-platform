package com.file.sharing.rest.dto.directory;

import java.time.Instant;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.file.sharing.rest.dto.ItemDetailsDTO;
import com.file.sharing.rest.dto.directory.DirectoryDetailsDTO.DirectoryDetailsDTOBuilder;

/**
 * @author Alexandru Mihai
 * @created Jan 6, 2018
 * 
 */
@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = DirectoryDetailsDTOBuilder.class)
public class DirectoryDetailsDTO extends ItemDetailsDTO {

	private final Instant creationTime;

	protected DirectoryDetailsDTO(DirectoryDetailsDTOBuilder builder) {
		super(builder);
		this.creationTime = Objects.requireNonNull(builder.creationTime);
	}

	public Instant getCreationTime() {
		return creationTime;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 4, 2017
	 */
	@JsonPOJOBuilder(withPrefix = "with", buildMethodName = "build")
	public static class DirectoryDetailsDTOBuilder
			extends ItemBuilder<DirectoryDetailsDTO, DirectoryDetailsDTOBuilder> {

		private Instant creationTime;

		public DirectoryDetailsDTOBuilder withCreationTime(Instant creationTime) {
			this.creationTime = creationTime;
			return getThis();
		}

		@Override
		public DirectoryDetailsDTO build() {
			return new DirectoryDetailsDTO(this);
		}

	}

}
