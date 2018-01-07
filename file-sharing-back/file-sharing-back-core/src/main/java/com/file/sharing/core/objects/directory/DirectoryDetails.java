package com.file.sharing.core.objects.directory;

import java.time.Instant;
import java.util.Objects;

import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.ItemDetailsVisitor;

/**
 * @author Alexandru Mihai
 * @created Nov 4, 2017
 */
public final class DirectoryDetails extends ItemDetails {

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

		public DirectoryBuilder withCreationTime(Instant creationTime) {
			this.creationTime = creationTime;
			return getThis();
		}

		@Override
		public DirectoryDetails build() {
			return new DirectoryDetails(this);
		}

	}

	@Override
	public void accept(ItemDetailsVisitor itemDetailsVisitior) {
		itemDetailsVisitior.visit(this);
	}

}
