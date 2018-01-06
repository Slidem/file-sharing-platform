package com.file.sharing.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.common.serializer.ItemActionId;

/**
 * @author Alexandru Mihai
 * @created Dec 29, 2017
 * 
 */
@JsonInclude(value = Include.NON_NULL)
public class ItemActionResponseDTO {

	@ItemActionId
	@JsonUnwrapped
	private ItemActionDTO itemActionDTO;

	public ItemActionResponseDTO() {
	}

	private ItemActionResponseDTO(ItemActionDTO itemActionDTO) {
		this.itemActionDTO = itemActionDTO;
	}

	public static ItemActionResponseDTO of(ItemActionDTO itemActionDTO) {
		return new ItemActionResponseDTO(itemActionDTO);
	}

	public ItemActionDTO getItemActionDTO() {
		return itemActionDTO;
	}

	public void setItemActionDTO(ItemActionDTO itemActionDTO) {
		this.itemActionDTO = itemActionDTO;
	}

}
