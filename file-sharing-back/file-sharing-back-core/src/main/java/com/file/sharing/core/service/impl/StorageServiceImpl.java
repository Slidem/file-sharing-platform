package com.file.sharing.core.service.impl;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.file.sharing.core.objects.StorageInfo;
import com.file.sharing.core.service.StorageService;
import com.file.sharing.core.utils.FileUtils;

/**
 * @author Alexandru Mihai
 * @created Nov 12, 2017
 */
@Component
public class StorageServiceImpl implements StorageService {
	
	private String storagePath;
	
	@Autowired
	public StorageServiceImpl(Environment env) {
		storagePath = env.getProperty("storage.path");
	}

	@Override
	public String getUserStoragePath(Integer userId) {
		if(userId == null) {
			throw new IllegalArgumentException();
		}
		return storagePath + userId;
	}
	
	@Override
	public StorageInfo getUserStorageInfo(Integer userId) {
		String location = getUserStoragePath(userId);
		Path rootFolder = Paths.get(location);
		return new StorageInfo(location, FileUtils.getItemSize(rootFolder));
	}
}
