package com.file.sharing.rest.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.sharing.core.objects.file.FileData;
import com.file.sharing.core.service.ItemsService;
import com.file.sharing.core.utils.FileCategoryUtil;

/**
 * @author Alexandru Mihai
 * @created Dec 10, 2017
 * 
 *          TEST ONLY... to be removed in the near future :)
 * 
 */
@RestController
public class DummyController {

	private ItemsService itemService;

	@Autowired
	public DummyController(ItemsService itemService) {
		this.itemService = itemService;
	}

	@PostMapping(value = "/uploadFile")
	public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile file) {
		String fileName = file.getOriginalFilename();

		FileData fileData;
		try {
			fileData = new FileData.Builder().withFileName(fileName)
					.withExtension(FileCategoryUtil.getExtensionFromFileName(fileName)).withBytes(file.getBytes())
					.build();

			itemService.uploadFile(null, fileData);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
