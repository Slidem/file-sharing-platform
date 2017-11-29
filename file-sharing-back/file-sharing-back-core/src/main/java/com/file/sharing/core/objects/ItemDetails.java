package com.file.sharing.core.objects;

import java.time.Instant;
import java.util.Objects;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public abstract class ItemDetails {

	private final Integer id;

	private final String name;

	private final Integer parent;

	private final String path;

	private final Long size;

	private final Instant lastModified;

	/**
	 * @param builder
	 */
	protected ItemDetails(ItemBuilder<?, ?> builder) {
		this.id = Objects.requireNonNull(builder.id);
		this.name = Objects.requireNonNull(builder.name);
		this.parent = builder.parent;
		this.path = Objects.requireNonNull(builder.path);
		this.size = Objects.requireNonNull(builder.size);
		this.lastModified = Objects.requireNonNull(builder.lastModified);
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getParent() {
		return parent;
	}

	public String getPath() {
		return path;
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
		
		private Integer id;

		private String name;

		private Integer parent;

		private String path;

		private Long size;

		private Instant lastModified;

		public B withId(Integer id) {
			this.id = id;
			return getThis();
		}

		public B withName(String name) {
			this.name = name;
			return getThis();
		}

		public B withParent(Integer parent) {
			this.parent = parent;
			return getThis();
		}

		public B withPath(String path) {
			this.path = path;
			return getThis();
		}

		public B withSize(Long size) {
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
