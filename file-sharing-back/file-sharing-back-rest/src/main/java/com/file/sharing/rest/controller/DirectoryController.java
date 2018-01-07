package com.file.sharing.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.file.sharing.core.actions.ItemAction;
import com.file.sharing.core.objects.Context;
import com.file.sharing.core.service.ItemActionService;
import com.file.sharing.rest.dto.CreateDirectoryDTO;
import com.file.sharing.rest.dto.ItemActionResponseDTO;
import com.file.sharing.rest.dto.RenameItemDTO;
import com.file.sharing.rest.factory.ItemActionDtoFactory;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
@RestController
@RequestMapping(value = "/items")
public class DirectoryController {

	@Autowired
	protected ItemActionService itemActionService;

	@Autowired
	protected Context context;

	@Autowired
	protected ItemActionDtoFactory itemActionDtoFactory;

	@PostMapping(value = "/directories", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO createDirectory(@RequestBody CreateDirectoryDTO createDirectoryDTO) {
		ItemAction itemAction = itemActionService.createDirectory(createDirectoryDTO.getParentId(), createDirectoryDTO.getDirectoryName());
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/directories/{directoryId}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO renameDirectory(@RequestBody RenameItemDTO renameItemDTO,
			                                     @PathVariable Integer directoryId) {
		ItemAction itemAction = itemActionService.renameDirectory(directoryId, renameItemDTO.getNewName());
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}

	@PatchMapping(value = "/directories/{directoryId}/move/{newDirectoryId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO moveDirectory(@PathVariable Integer directoryId,
			@PathVariable(required = false) Integer newDirectoryId) {
		ItemAction itemAction = itemActionService.moveDirectory(directoryId, newDirectoryId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}
	
	@DeleteMapping(value = "/directories/{directoryId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public ItemActionResponseDTO deleteDirectory(@PathVariable Integer directoryId) {
		ItemAction itemAction = itemActionService.deleteDirectory(directoryId);
		return ItemActionResponseDTO.of(itemActionDtoFactory.toDto(itemAction));
	}
}
