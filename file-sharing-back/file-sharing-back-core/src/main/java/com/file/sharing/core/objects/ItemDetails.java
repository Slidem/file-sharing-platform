package com.file.sharing.core.objects;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public abstract class ItemDetails {

	private final String name;

	private final Integer parent;

	private final String fullPath;

	private final Long size;

	private final Instant lastModified;

	/**
	 * @param builder
	 */
	protected ItemDetails(ItemBuilder<?, ?> builder) {
		this.name = Objects.requireNonNull(builder.name);
		this.parent = Objects.requireNonNull(builder.parent);
		this.fullPath = Objects.requireNonNull(builder.fullPath);
		this.size = Objects.requireNonNull(builder.size);
		this.lastModified = Objects.requireNonNull(builder.lastModified);
	}

	public String getName() {
		return name;
	}

	public Integer getParent() {
		return parent;
	}

	public String getFullPath() {
		return fullPath;
	}

	public Long getSize() {
		return size;
	}
	
	public Instant getLastModified() {
		return lastModified;
	}

	/**
	 * @author Alexandru Mihai
	 * @created Nov 4, 2017
	 */
	public abstract static class ItemBuilder<T extends ItemDetails, B extends ItemBuilder<?, ?>> {
		
		private String name;

		private Integer parent;

		private String fullPath;

		private Long size;

		private Instant lastModified;

		public B setName(String name) {
			this.name = name;
			return getThis();
		}

		public B setParent(Integer parent) {
			this.parent = parent;
			return getThis();
		}

		public B setFullPath(String fullPath) {
			this.fullPath = fullPath;
			return getThis();
		}

		public B setSize(Long size) {
			this.size = size;
			return getThis();
		}

		public B withLastModified(Instant lastModified) {
			this.lastModified = lastModified;
			return getThis();
		}

		@SuppressWarnings("unchecked")
		protected B getThis() {
			return (B) this;
		}

		protected abstract T build();

	}

}
