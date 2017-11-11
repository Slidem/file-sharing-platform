package com.file.sharing.core.objects.directory;

import java.time.Instant;
import java.util.Objects;

import com.file.sharing.core.objects.ItemDetails;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public class DirectoryDetails extends ItemDetails {

	private final Instant creationTime;

	protected DirectoryDetails(DirectoryBuilder builder) {
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
	public static class DirectoryBuilder extends ItemBuilder<DirectoryDetails, DirectoryBuilder> {

		private Instant creationTime;

		public DirectoryBuilder setCreationTime(Instant creationTime) {
			this.creationTime = creationTime;
			return getThis();
		}

		@Override
		protected DirectoryDetails build() {
			return new DirectoryDetails(this);
		}

	}

}
