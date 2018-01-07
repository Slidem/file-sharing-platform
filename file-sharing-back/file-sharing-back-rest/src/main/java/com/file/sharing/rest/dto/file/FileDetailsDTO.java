package com.file.sharing.rest.dto.file;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.rest.dto.ItemDetailsDTO;
import com.file.sharing.rest.dto.file.FileDetailsDTO.FileDetailsDTOBuilder;

/**
 * @author Alexandru Mihai
 * @created Jan 6, 2018
 * 
 */
@JsonInclude(value = Include.NON_NULL)
@JsonDeserialize(builder = FileDetailsDTOBuilder.class)
public class FileDetailsDTO extends ItemDetailsDTO {

	private final FileCategories category;

	private final String extension;

	private final Instant uploadTime;

	protected FileDetailsDTO(FileDetailsDTOBuilder builder) {
		super(builder);
		this.category = builder.category;
		this.extension = builder.extension;
		this.uploadTime = builder.uploadTime;
	}

	public FileCategories getCategory() {
		return category;
	}

	public String getExtension() {
		return extension;
	}

	public Instant getUploadTime() {
		return uploadTime;
	}

	@JsonPOJOBuilder(withPrefix = "with", buildMethodName = "buildFileDetailsDTOBuilder")
	public static class FileDetailsDTOBuilder extends ItemBuilder<FileDetailsDTO, FileDetailsDTOBuilder> {

		private FileCategories category;

		private String extension;

		private Instant uploadTime;

		public FileDetailsDTOBuilder withCategory(FileCategories category) {
			this.category = category;
			return getThis();
		}

		public FileDetailsDTOBuilder withExtension(String extension) {
			this.extension = extension;
			return getThis();
		}

		public FileDetailsDTOBuilder withUploadTime(Instant uploadTime) {
			this.uploadTime = uploadTime;
			return getThis();
		}

		@Override
		public FileDetailsDTO build() {
			return new FileDetailsDTO(this);
		}
	}
}
