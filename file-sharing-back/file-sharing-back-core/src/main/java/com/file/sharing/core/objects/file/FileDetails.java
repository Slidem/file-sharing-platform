package com.file.sharing.core.objects.file;

import java.time.Instant;

import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.ItemDetailsVisitor;

public class FileDetails extends ItemDetails {

	private final FileCategories category;

	private final String extension;

	private final Instant uploadTime;

	protected FileDetails(FileDetailsBuilder builder) {
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

	@Override
	public void accept(ItemDetailsVisitor itemDetailsVisitior) {
		itemDetailsVisitior.visit(this);
	}


	public static class FileDetailsBuilder extends ItemBuilder<FileDetails, FileDetailsBuilder> {

		private FileCategories category;

		private String extension;

		private Instant uploadTime;

		public FileDetailsBuilder withCategory(FileCategories category) {
			this.category = category;
			return getThis();
		}

		public FileDetailsBuilder withExtension(String extension) {
			this.extension = extension;
			return getThis();
		}

		public FileDetailsBuilder withUploadTime(Instant uploadTime) {
			this.uploadTime = uploadTime;
			return getThis();
		}

		@Override
		public FileDetails build() {
			return new FileDetails(this);
		}
	}

}
