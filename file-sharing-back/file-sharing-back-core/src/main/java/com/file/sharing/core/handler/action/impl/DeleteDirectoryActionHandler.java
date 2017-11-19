package com.file.sharing.core.handler.action.impl;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.file.sharing.core.actions.directory.DeleteDirectoryAction;
import com.file.sharing.core.handler.action.AbstractItemActionHandler;
import com.file.sharing.core.objects.file.FileActionStatus;
import com.file.sharing.core.service.ItemDetailsService;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Component
public class DeleteDirectoryActionHandler extends AbstractItemActionHandler<DeleteDirectoryAction> {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @param applicationEventPublisher
	 * @param itemDetailsService
	 */
	@Autowired
	public DeleteDirectoryActionHandler(ApplicationEventPublisher applicationEventPublisher,
			ItemDetailsService itemDetailsService) {
		super(applicationEventPublisher);
	}

	@Override
	public Class<DeleteDirectoryAction> getActionClass() {
		return DeleteDirectoryAction.class;
	}

	@Override
	protected FileActionStatus handleAction(DeleteDirectoryAction itemActionEvent) {
		String path = itemActionEvent.getPath();
		String name = itemActionEvent.getItemName();

		Path fullPath = Paths.get(path, name);

		try {
			Files.walkFileTree(fullPath, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					Files.delete(dir);
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (IOException e) {
			logger.info(e.getMessage(), e);
			return FileActionStatus.FAILURE;
		} catch (Exception e) {
			logger.warn(e.getMessage(), e);
			return FileActionStatus.FAILURE;
		}

		return FileActionStatus.SUCCESS;
	}

}