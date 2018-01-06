package com.file.sharing.core.business.impl;

import static com.file.sharing.core.objects.impl.ItemActionInfoImpl.newBuilder;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.file.sharing.core.business.ItemInfoBusiness;
import com.file.sharing.core.dao.FileItemDao;
import com.file.sharing.core.dao.ItemActionDao;
import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.ItemActionEntity;
import com.file.sharing.core.factory.BasicItemInfoFactory;
import com.file.sharing.core.objects.BasicItemInfo;
import com.file.sharing.core.objects.ItemActionInfo;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.impl.PageResultImpl;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.search.OrderValue;

/**
 * @author Alexandru Mihai
 * @created Dec 26, 2017
 * 
 */
@Component
public class ItemInfoBusinessImpl implements ItemInfoBusiness {

	private final FileItemDao fileItemDao;

	private final ItemDao itemDao;

	private final ItemActionDao itemActionDao;

	private final BasicItemInfoFactory basicItemInfoFactory;



	@Autowired
	public ItemInfoBusinessImpl(FileItemDao fileItemDao, ItemDao itemDao, ItemActionDao itemActionDao,
			BasicItemInfoFactory basicItemInfoFactory) {
		this.fileItemDao = fileItemDao;
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
				          .map(e -> newBuilder().withActionTime(e.getActionTime().toInstant())
				        		                .withActionType(e.getActionType())
				        		                .withBasicItemInfo(basicItemInfo)
				        		                .build())
				          .collect(Collectors.toList());
		//@formatter:on
	}

}
