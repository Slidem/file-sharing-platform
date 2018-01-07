package com.file.sharing.rest.validator;

import static com.file.sharing.common.dto.error.ErrorCode.INVALID_VALUE;
import static com.file.sharing.common.dto.error.ErrorCode.MANDATORY_VALUE;

import java.util.Arrays;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.file.sharing.core.objects.file.FileCategories;
import com.file.sharing.core.search.ItemSearchOrder;
import com.file.sharing.core.search.OrderValue;
import com.file.sharing.rest.dto.ItemSearchDTO;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
@Component
public class ItemSearchValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.equals(ItemSearchDTO.class);
	}

	@Override
	public void validate(Object target, Errors errors) {

		if (!(target instanceof ItemSearchDTO)) {
			throw new IllegalArgumentException("Whops, wrong type of class has been passed to the validator.");
		}

		ItemSearchDTO itemSearchDTO = (ItemSearchDTO) target;

		checkPageParams(itemSearchDTO, errors);
		checkOrder(itemSearchDTO, errors);
		checkCategories(itemSearchDTO, errors);

	}

	private void checkCategories(ItemSearchDTO itemSearchDTO, Errors errors) {

		Set<String> categories = itemSearchDTO.getCategories();
		if (categories == null) {
			return;
		}
		//@formatter:off
		categories.stream()
		          .filter(c -> FileCategories.valueOf(c) == null)
		          .findFirst()
		          .ifPresent(c -> errors.reject(INVALID_VALUE.name(), "File categories must be one of the following values: " + Arrays.toString(FileCategories.values())));
		//@formatter:on
	}

	private void checkOrder(ItemSearchDTO itemSearchDTO, Errors errors) {
		String orderValue = itemSearchDTO.getOrderValue();
		String orderCriteria = itemSearchDTO.getOrderCriteria();

		if (orderValue == null && orderCriteria == null) {
			return;
		}

		if (orderValue == null || orderCriteria == null) {
			errors.reject(INVALID_VALUE.name(),
					"Order criteria and order value must be both be present or not present at all."
							+ Arrays.toString(ItemSearchOrder.values()));
		}

		if (OrderValue.valueOf(orderValue) == null) {
			errors.reject(INVALID_VALUE.name(),
					"Order value must be one of the following: " + Arrays.toString(OrderValue.values()));
		}

		if (ItemSearchOrder.valueOf(orderCriteria) == null) {
			errors.reject(INVALID_VALUE.name(),
					"Order criteria value must be one of the following: " + Arrays.toString(ItemSearchOrder.values()));
		}
	}

	private void checkPageParams(ItemSearchDTO itemSearchDTO, Errors errors) {
		Integer pageSize = itemSearchDTO.getPageSize();
		Integer pageNumber = itemSearchDTO.getPageNumber();

		if (pageSize == null || pageNumber == null) {
			errors.reject(MANDATORY_VALUE.name(), "Page number and page size cannot be null");
			return;
		}

		if (pageSize < 1 && pageNumber < 1) {
			errors.reject(INVALID_VALUE.name(), "Page number and page size should have values strictly bigger than 0");
		}
	}

}
