package com.file.sharing.rest.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.objects.file.FileData;
import com.file.sharing.core.service.ItemService;
import com.file.sharing.core.utils.FileCategoryUtil;
import com.file.sharing.rest.dto.ItemActionResponseDTO;
import com.file.sharing.rest.dto.RenameItemDTO;

/**
 * @author Alexandru Mihai
 * @created Dec 30, 2017
 * 
 */
public class FilesController extends ItemController {

	@Autowired
	private ItemService itemService;

	@PostMapping(value = "/files", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "directoryId", required = false) Integer directoryId) throws IOException {

		String fileName = file.getOriginalFilename();

		FileData fileData = new FileData.Builder()
				.withFileName(fileName)
				.withExtension(FileCategoryUtil.getExtensionFromFileName(fileName))
				.withBytes(file.getBytes())
				.build();
		
		ItemAction itemAction = itemActionService.uploadFile(directoryId, fileData);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/files/{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO renameFile(@RequestBody RenameItemDTO renameItemDTO, @PathVariable Integer itemId) {
		ItemAction itemAction = itemActionService.renameFile(itemId, renameItemDTO.getNewName());
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/files/{fileId}/move/{newDirectoryId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO moveFile(@PathVariable Integer fileId,
			@PathVariable(required = false) Integer newDirectoryId) {
		ItemAction itemAction = itemActionService.moveFile(fileId, newDirectoryId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/files/{fileId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO deleteDirectory(@PathVariable Integer fileId) {
		ItemAction itemAction = itemActionService.deleteFile(fileId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@GetMapping(value = "/downloadFile", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public FileSystemResource downloadFile(@RequestParam(value = "fileId") Integer fileId, HttpServletResponse response)
			throws IOException {
		File file = itemService.retrieveFile(fileId);
		response.setHeader("Content-Dispition", "attachementl; filename=\"" + file.getName() + "\"");
		return new FileSystemResource(file);
	}
	

}
