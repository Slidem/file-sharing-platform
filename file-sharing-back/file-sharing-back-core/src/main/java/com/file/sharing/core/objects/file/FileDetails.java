package com.file.sharing.core.objects.file;

import java.time.Instant;

import com.file.sharing.core.objects.ItemDetails;

public class FileDetails extends ItemDetails {

	private final FileCategories category;

	private final String extension;

	private final Instant uploadTime;

	private final Integer version;

	protected FileDetails(FileDetailsBuilder builder) {
		super(builder);
		this.category = builder.category;
		this.extension = builder.extension;
		this.uploadTime = builder.uploadTime;
		this.version = builder.version;
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

	public Integer getVersion() {
		return version;
	}

	public static class FileDetailsBuilder extends ItemBuilder<FileDetails, FileDetailsBuilder> {

		private FileCategories category;

		private String extension;

		private Instant uploadTime;

		private Integer version;

		public FileDetailsBuilder setCategory(FileCategories category) {
			this.category = category;
			return getThis();
		}

		public FileDetailsBuilder setExtension(String extension) {
			this.extension = extension;
			return getThis();
		}

		public FileDetailsBuilder setUploadTime(Instant uploadTime) {
			this.uploadTime = uploadTime;
			return getThis();
		}

		public FileDetailsBuilder setVersion(Integer version) {
			this.version = version;
			return getThis();
		}

		@Override
		protected FileDetails build() {
			return new FileDetails(this);
		}
	}

}
