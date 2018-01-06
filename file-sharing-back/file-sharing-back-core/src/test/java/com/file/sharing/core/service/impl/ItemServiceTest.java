package com.file.sharing.core.service.impl;

import java.io.File;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.ItemNotFoundException;

/**
 * @author Alexandru Mihai
 * @created Nov 11, 2017
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemServiceTest {

	private final static int INVALID_ITEM_ID = 1;

	private final static int VALID_ITEM_ID = 2;

	@Mock
	private ItemDao itemDao;

	@InjectMocks
	private ItemServiceImpl unit;

	@Mock
	private Item item;

	@Test(expected = ItemNotFoundException.class)
	public void testGetItemPathThrowsExceptionWhenItemNotFound() {
		
		Mockito.when(itemDao.find(INVALID_ITEM_ID)).thenReturn(Optional.empty());
		
		unit.getItemFullPath(INVALID_ITEM_ID);

	}

	@Test
	public void testGetItemPathReturnsValidPathWhenItemIsFound() {

		Mockito.when(itemDao.find(VALID_ITEM_ID)).thenReturn(Optional.of(item));
		Mockito.when(item.getPath()).thenReturn("dummyPath");
		Mockito.when(item.getName()).thenReturn("dummyFileName");

		String result = unit.getItemFullPath(VALID_ITEM_ID);

		Assert.assertEquals("dummyPath" + File.separator + "dummyFileName", result);
	}
	
	public void testGetItemsByParentId() {
		
		
	}

}
