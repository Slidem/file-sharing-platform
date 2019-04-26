package com.file.sharing.core.business.impl;

import com.file.sharing.core.business.FileItemCategoryBusiness;
import com.file.sharing.core.dao.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Andrei Aioanei
 * @created 21.04.2019
 */
@RunWith(MockitoJUnitRunner.class)
public class ItemsActionBusinessImplTest {

    private String COPY_FILE_FORMAT = "File name (Copy %d).jpg";

    private ItemsActionBusinessImpl itemsActionBusinessImpl;

    @Mock
    private DirectoryItemDao directoryItemDao;

    @Mock
    private ItemActionDao itemActionDao;

    @Mock
    private ItemDao itemDao;

    @Mock
    private UsersDao usersDao;

    @Mock
    private FileItemCategoryBusiness fileItemCategoryBusiness;

    @Mock
    private FileItemDao fileItemDao;

    @Before
    public void init() {
        itemsActionBusinessImpl = new ItemsActionBusinessImpl(directoryItemDao, itemActionDao, itemDao, usersDao, fileItemCategoryBusiness, fileItemDao);
    }

    @Test
    public void test_getNextAvailableSuffixNumber_valid() {
        List<String> mockNameList = new ArrayList<>();
        for(int i=1; i<15; i++) {
            mockNameList.add(String.format(COPY_FILE_FORMAT, i));
        }

        int result = itemsActionBusinessImpl.getNextAvailableSuffixNumber(mockNameList);
        Assert.assertEquals(15, result);
    }

    @Test
    public void test_getNextAvailableSuffixNumber_valid_1() {
        List<String> mockNameList = new ArrayList<>();
        mockNameList.add(String.format(COPY_FILE_FORMAT, 1));

        int result = itemsActionBusinessImpl.getNextAvailableSuffixNumber(mockNameList);
        Assert.assertEquals(1, result);
    }

    @Test
    public void test_getDuplicateNameWithSuffix_valid() {
        String result = itemsActionBusinessImpl.getDuplicateNameWithSuffix("filename.jpg", " (Copy 2)");
        Assert.assertEquals("filename (Copy 2).jpg", result);
    }

}
