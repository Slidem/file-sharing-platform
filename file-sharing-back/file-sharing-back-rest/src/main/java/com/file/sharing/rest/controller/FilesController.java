package com.file.sharing.rest.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.objects.PageResult;
import com.file.sharing.core.objects.file.BasicFileInfo;
import com.file.sharing.core.objects.file.FileData;
import com.file.sharing.core.search.ItemSearch;
import com.file.sharing.core.service.ItemActionService;
import com.file.sharing.core.service.ItemService;
import com.file.sharing.core.utils.FileCategoryUtil;
import com.file.sharing.rest.dto.ItemActionResponseDTO;
import com.file.sharing.rest.dto.ItemSearchDTO;
import com.file.sharing.rest.dto.PageResultDTO;
import com.file.sharing.rest.dto.RenameItemDTO;
import com.file.sharing.rest.dto.file.BasicFileInfoDTO;
import com.file.sharing.rest.factory.ItemActionDtoFactory;
import com.file.sharing.rest.factory.ItemSearchFactory;
import com.file.sharing.rest.factory.PageResultFactory;
import com.file.sharing.rest.validator.ItemSearchValidator;

/**
 * @author Alexandru Mihai
 * @created Dec 30, 2017
 * 
 */
@RestController
@RequestMapping(value = "/items/files")
public class FilesController {

	private ItemActionService itemActionService;

	private ItemActionDtoFactory itemActionDtoFactory;

	private ItemService itemService;

	private PageResultFactory pageResultFactory;

	private ItemSearchValidator itemSearchValidator;

	private ItemSearchFactory itemSearchFactory;

	@Autowired
	public FilesController(ItemActionService itemActionService, ItemActionDtoFactory itemActionDtoFactory,
			ItemService itemService, PageResultFactory pageResultFactory, ItemSearchValidator itemSearchValidator,
			ItemSearchFactory itemSearchFactory) {
		this.itemActionService = itemActionService;
		this.itemActionDtoFactory = itemActionDtoFactory;
		this.itemService = itemService;
		this.pageResultFactory = pageResultFactory;
		this.itemSearchValidator = itemSearchValidator;
		this.itemSearchFactory = itemSearchFactory;
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO uploadFile(@RequestParam(value = "file", required = true) MultipartFile file,
			@RequestParam(value = "directoryId", required = false) Integer directoryId) throws IOException {

		String fileName = file.getOriginalFilename();

		//@formatter:off
		FileData fileData = new FileData.Builder()
				.withFileName(fileName)
				.withExtension(FileCategoryUtil.getExtensionFromFileName(fileName))
				.withBytes(file.getBytes())
				.build();
		//@formatter:on

		ItemAction itemAction = itemActionService.uploadFile(directoryId, fileData);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "{itemId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO renameFile(@RequestBody RenameItemDTO renameItemDTO, @PathVariable Integer itemId) {
		ItemAction itemAction = itemActionService.renameFile(itemId, renameItemDTO.getNewName());
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/{fileId}/move/{newDirectoryId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO moveFile(@PathVariable Integer fileId,
			@PathVariable(required = false) Integer newDirectoryId) {
		ItemAction itemAction = itemActionService.moveFile(fileId, newDirectoryId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/{fileId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO deleteFile(@PathVariable Integer fileId) {
		ItemAction itemAction = itemActionService.deleteFile(fileId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@GetMapping(value = "/{fileId}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public FileSystemResource downloadFile(@PathVariable(value = "fileId") Integer fileId, HttpServletResponse response)
			throws IOException {
		File file = itemService.retrieveFile(fileId);
		response.setHeader("Content-Dispition", "attachementl; filename=\"" + file.getName() + "\"");
		return new FileSystemResource(file);
	}

	@GetMapping
	public PageResultDTO<BasicFileInfoDTO> searchFiles(@Valid ItemSearchDTO itemSearchDTO) {
		ItemSearch itemSearch = itemSearchFactory.fromDto(itemSearchDTO);
		PageResult<BasicFileInfo> pageResult = itemService.searchFiles(itemSearch);
		return pageResultFactory.toBasicFileInfoResultDto(pageResult);
	}

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(itemSearchValidator);
	}

}
