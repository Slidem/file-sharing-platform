package com.file.sharing.core.service.impl;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.file.sharing.core.dao.ItemDao;
import com.file.sharing.core.entity.DirectoryItem;
import com.file.sharing.core.entity.FileItem;
import com.file.sharing.core.entity.Item;
import com.file.sharing.core.exception.ItemNotFoundException;
import com.file.sharing.core.factory.BasicItemInfoFactory;
import com.file.sharing.core.factory.impl.BasicItemInfoFactoryImpl;
import com.file.sharing.core.objects.BasicItemInfo;

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

	@Mock
	private BasicItemInfoFactory basicItemInfoFactory;

	@InjectMocks
	private ItemServiceImpl unit;

	@Mock
	private Item item1;
	
	@Mock
	private Item item2;
	
	@Mock
	private BasicItemInfo basicItemInfo1;
	
	@Mock
	private BasicItemInfo basicItemInfo2;

	@Test(expected = ItemNotFoundException.class)
	public void testGetItemPathThrowsExceptionWhenItemNotFound() {
		
		Mockito.when(itemDao.find(INVALID_ITEM_ID)).thenReturn(Optional.empty());
		
		unit.getItemFullPath(INVALID_ITEM_ID);

	}

	@Test
	public void testGetItemPathReturnsValidPathWhenItemIsFound() {

		Mockito.when(itemDao.find(VALID_ITEM_ID)).thenReturn(Optional.of(item1));
		Mockito.when(item1.getPath()).thenReturn("dummyPath");
		Mockito.when(item1.getName()).thenReturn("dummyFileName");

		String result = unit.getItemFullPath(VALID_ITEM_ID);

		Assert.assertEquals("dummyPath" + File.separator + "dummyFileName", result);
	}
	
	@Test
	public void getBasicItemInfoByParentId() {
		List<Item> itemList1 = Lists.newArrayList(item1, item2);
		
		Mockito.when(itemDao.getItemsByParentId(VALID_ITEM_ID)).thenReturn(itemList1);
		Mockito.when(basicItemInfoFactory.fromItemEntity(item1)).thenReturn(basicItemInfo1);
		Mockito.when(basicItemInfoFactory.fromItemEntity(item2)).thenReturn(basicItemInfo2);

		List<BasicItemInfo> basicItemInfoList1 = Lists.newArrayList(basicItemInfoFactory.fromItemEntity(item1), basicItemInfoFactory.fromItemEntity(item2));
		List<BasicItemInfo> result = unit.getBasicItemInfoByParentId(VALID_ITEM_ID);
		
		Assert.assertTrue(result.equals(basicItemInfoList1));
	}

}
