package com.file.sharing.core.handler.action;

import java.time.Instant;

import org.springframework.context.ApplicationEventPublisher;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.events.ItemActionEvent;
import com.file.sharing.core.events.impl.ItemActionEventImpl;
import com.file.sharing.core.objects.file.FileActionStatus;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
public abstract class AbstractItemActionHandler<T extends ItemAction> implements ItemActionHandler<T> {

	private ApplicationEventPublisher applicationEventPublisher;

	public AbstractItemActionHandler(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;
	}

	@Override
	public void handle(final T action) {

		final long start = Instant.now().getNano();

		FileActionStatus status = handleAction(action);

		final long finish = Instant.now().getNano();

		ItemActionEvent<T> itemActionEvent = getItemActionEvent(action, status, finish - start);

		applicationEventPublisher.publishEvent(itemActionEvent);
	}

	private ItemActionEvent<T> getItemActionEvent(T action, FileActionStatus status, long duration) {
		return new ItemActionEventImpl.Builder<T>()
				.withDuration(duration)
				.withFileActionStatus(status)
				.withItemAction(action)
				.build();
		
	}

	protected abstract FileActionStatus handleAction(T action);


}
