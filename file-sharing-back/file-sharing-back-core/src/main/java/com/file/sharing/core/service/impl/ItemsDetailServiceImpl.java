package com.file.sharing.core.service.impl;

import java.io.File;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.objects.directory.DirectoryDetails;
import com.file.sharing.core.objects.file.FileDetails;
import com.file.sharing.core.service.ItemDetailsService;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@Service
@Transactional(readOnly = true)
public class ItemsDetailServiceImpl implements ItemDetailsService {

	private ItemDao itemDao;

	@Autowired
	public ItemsDetailServiceImpl(ItemDao itemDao) {
		this.itemDao = itemDao;
	}

	@Override
	public String getItemFullPath(int itemId) {
		Optional<Item> item = itemDao.find(itemId);

		String fullPath = item.map(this::getFullPath).orElse(null);

		if (fullPath == null) {
			throw new ItemNotFoundException("Item not found for id: " + itemId);
		}

		return fullPath;
	}

	@Override
	public DirectoryDetails getDirectoryDetails(int directoryId) {
		// TODO IMPLEMENT !!!
		return null;
	}

	@Override
	public FileDetails getFileDetails(int fileId) {
		// TODO IMPLEMENT !!!
		return null;
	}

	private String getFullPath(Item item) {
		return item.getPath() + File.separator + item.getName();
	}



}
