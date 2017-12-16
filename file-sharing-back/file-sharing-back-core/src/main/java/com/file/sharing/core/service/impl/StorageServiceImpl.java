package com.file.sharing.core.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.file.sharing.core.exception.UserStorageNotFoundException;
import com.file.sharing.core.objects.StorageInfo;
import com.file.sharing.core.service.StorageService;

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
		BasicFileAttributes basicFileAttr;
		
		try {
			basicFileAttr= Files.readAttributes(rootFolder, BasicFileAttributes.class);
		} catch (IOException e) {
			throw new UserStorageNotFoundException(rootFolder.toString(), e);
		}
		
		return new StorageInfo(location, basicFileAttr.size());
	}
}
