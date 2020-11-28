package com.file.sharing.core.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FileNameUtilsTest {

    private static final String COPY_FILE_FORMAT = "File name (Copy %d).jpg";

    @Test
    public void test_getNextAvailableSuffixNumber_valid() {
        List<String> mockNameList = new ArrayList<>();
        for(int i=1; i<15; i++) {
            mockNameList.add(String.format(COPY_FILE_FORMAT, i));
        }

        int result = FileNameUtils.getNextAvailableSuffixNumber(mockNameList);
        Assert.assertEquals(15, result);
    }

    @Test
    public void test_getNextAvailableSuffixNumber_valid_randomCopiesList() {
        List<String> mockNameList = new ArrayList<>();
        mockNameList.add(String.format(COPY_FILE_FORMAT, 1));
        mockNameList.add(String.format(COPY_FILE_FORMAT, 2));
        mockNameList.add(String.format(COPY_FILE_FORMAT, 5));
        mockNameList.add(String.format(COPY_FILE_FORMAT, 10));
        int result = FileNameUtils.getNextAvailableSuffixNumber(mockNameList);

        Assert.assertEquals(3, result);
    }

    @Test
    public void test_getNextAvailableSuffixNumber_valid_emptyList() {
        List<String> mockNameList = new ArrayList<>();
        int result = FileNameUtils.getNextAvailableSuffixNumber(mockNameList);

        Assert.assertEquals(1, result);
    }

    @Test
    public void test_getDuplicateNameWithSuffix_valid() {
        String result = FileNameUtils.getDuplicateNameWithSuffix("filename.jpg", " (Copy 2)");
        Assert.assertEquals("filename (Copy 2).jpg", result);
    }
}
