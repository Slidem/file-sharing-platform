package com.file.sharing.core.business.impl;

import static com.file.sharing.core.objects.impl.ItemActionInfoImpl.newBuilder;
import static java.nio.file.Files.readAttributes;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.business.ItemInfoBusiness;
import com.file.sharing.core.dao.DirectoryItemDao;
import com.file.sharing.core.dao.FileItemDao;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.entity.ItemActionEntity;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.factory.BasicItemInfoFactory;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.core.objects.ItemDetails;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.objects.file.FileDetails.FileDetailsBuilder;
import com.file.sharing.core.objects.file.ItemType;
import com.file.sharing.core.objects.impl.PageResultImpl;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.OrderValue;
import com.file.sharing.core.utils.FileUtils;

/**
 * @author Alexandru Mihai
 * @created Dec 26, 2017
 * 
 */
@Component
public class ItemInfoBusinessImpl implements ItemInfoBusiness {

	private final FileItemDao fileItemDao;

	private final DirectoryItemDao directoryItemDao;

	private final ItemDao itemDao;

	private final ItemActionDao itemActionDao;

	private final BasicItemInfoFactory basicItemInfoFactory;


	@Autowired
	public ItemInfoBusinessImpl(FileItemDao fileItemDao, DirectoryItemDao directoryItemDao, ItemDao itemDao,
			ItemActionDao itemActionDao, BasicItemInfoFactory basicItemInfoFactory) {
		this.fileItemDao = fileItemDao;
		this.directoryItemDao = directoryItemDao;
		this.itemDao = itemDao;
		this.itemActionDao = itemActionDao;
		this.basicItemInfoFactory = basicItemInfoFactory;
	}

	@Override
	public PageResult<BasicFileInfo> getFilesInfo(ItemSearch itemSearch) {
		Objects.requireNonNull(itemSearch);
		PageResult<FileItem> pageResult = fileItemDao.getItemsBasedOnCriteria(itemSearch);
		List<BasicFileInfo> infos = pageResult.getResult().stream().map(basicItemInfoFactory::fromFileItemEntity).collect(Collectors.toList());
		return PageResultImpl.of(infos, pageResult.getTotalPageCount(), pageResult.getPageSize());
	}

	@Override
	public Optional<BasicItemInfo> getBasicItemInfo(Integer itemId) {
		return itemDao.find(itemId).map(basicItemInfoFactory::fromItemEntity);
	}

	@Override
	public List<ItemActionInfo> getItemActionsInfo(Integer itemId, OrderValue actionTimeOrder) {
		BasicItemInfo basicItemInfo = getBasicItemInfo(itemId).orElse(null);

		List<ItemActionEntity> actionInfos = itemActionDao.findActionsBasedOnItemId(itemId, actionTimeOrder);

		//@formatter:off
		return actionInfos.stream()
				          .map(e -> newBuilder()
				        		    .withActionTime(e.getActionTime().toInstant())
				        		    .withActionType(e.getActionType())
				        		    .withBasicItemInfo(basicItemInfo)
				        		    .build())
				          .collect(Collectors.toList());
		//@formatter:on
	}

	@Override
	public ItemDetails getItemDetails(Integer itemId) throws IOException {
		Item item = itemDao.find(itemId).orElseThrow(ItemNotFoundException::new);
		return isDirectory(item) ? getDirectoryDetails((DirectoryItem) item) : getFileDetails((FileItem) item);
	}

	@Override
	public DirectoryDetails getDirectoryDetails(Integer directoryId) throws IOException {
		DirectoryItem directoryItem = directoryItemDao.find(directoryId).orElseThrow(ItemNotFoundException::new);
		return getDirectoryDetails(directoryItem);
	}

	@Override
	public FileDetails getFileDetails(Integer fileId) throws IOException {
		FileItem fileItem = fileItemDao.find(fileId).orElseThrow(ItemNotFoundException::new);
		return getFileDetails(fileItem);
	}

	// -------------------------------------------------------------------

	private FileDetails getFileDetails(FileItem item) throws IOException {
		BasicFileAttributes attr = readBasicAttributes(item);
		FileDetailsBuilder builder = new FileDetailsBuilder();
		addCommonDetails(builder, item, attr);
		//@formatter:off
		return builder
			   .withUploadTime(item.getUploadTime().toInstant())
		       .withCategory(item.getCategory().getCategory())
		       .withExtension(item.getCategory().getExtension())
		       .build();
		//@formatter:on
	}

	private DirectoryDetails getDirectoryDetails(DirectoryItem directoryItem) throws IOException {
		BasicFileAttributes attr = readBasicAttributes(directoryItem);
		DirectoryDetails.DirectoryBuilder builder = new DirectoryDetails.DirectoryBuilder();
		addCommonDetails(builder, directoryItem, attr);
		return builder.withCreationTime(attr.creationTime().toInstant()).build();
	}


	private void addCommonDetails(ItemDetails.ItemBuilder<? extends ItemDetails, ?> builder, Item item,
			BasicFileAttributes attr)
			throws IOException {
		//@formatter:off
		builder.withId(item.getId())
		       .withLastModified(attr.lastModifiedTime().toInstant())
		       .withName(item.getName())
		       .withPath(item.getPath())
		       .withParent(Optional.ofNullable(item.getParent()).map(DirectoryItem::getId).orElse(null))
			   .withSize(FileUtils.getItemSize(Paths.get(item.getPath(), item.getName())));
		//@formatter:on
	}

	private BasicFileAttributes readBasicAttributes(Item item) throws IOException {
		return readAttributes(Paths.get(item.getPath(), item.getName()), BasicFileAttributes.class);
	}

	private boolean isDirectory(Item item) {
		return ItemType.DIRECTORY == item.getItemType();
	}

}
