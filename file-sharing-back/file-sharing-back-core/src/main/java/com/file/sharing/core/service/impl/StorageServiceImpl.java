package com.file.sharing.core.service.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.core.service.StorageService;

/**
 * @author Alexandru Mihai
 * @created Nov 12, 2017
 */
@Component
public class StorageServiceImpl implements StorageService {

	@Override
	public String getStoragePath(Integer userId) {
		// FIXME: just for test
		return "/home/slidem/Development/test";
	}

}
