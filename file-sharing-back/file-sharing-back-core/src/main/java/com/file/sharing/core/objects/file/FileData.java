package com.file.sharing.core.objects.file;

import java.util.Objects;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public class FileData {

	private final String fileName;

	private final String extension;

	private final byte[] bytes;

	private FileData(Builder builder) {
		this.fileName = Objects.requireNonNull(builder.fileName);
		this.extension = Objects.requireNonNull(builder.extension);
		this.bytes = Objects.requireNonNull(builder.bytes);
	}

	public String getFileName() {
		return fileName;
	}

	public String getExtension() {
		return extension;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public static class Builder{
		
		private String fileName;

		private String extension;

		private byte[] bytes;


		public Builder withFileName(String fileName) {
			this.fileName = fileName;
			return this;
		}


		public Builder withExtension(String extension) {
			this.extension = extension;
			return this;
		}


		public Builder withBytes(byte[] bytes) {
			this.bytes = bytes;
			return this;
		}
		
		public FileData build() {
			return new FileData(this);
		}
		
	}

}
