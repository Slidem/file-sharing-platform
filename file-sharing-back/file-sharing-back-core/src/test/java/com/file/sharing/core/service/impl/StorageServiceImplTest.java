package com.file.sharing.core.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.core.env.Environment;

@RunWith(MockitoJUnitRunner.class)
public class StorageServiceImplTest {
	
	public static final String STORAGE_PATH = "DUMMY_STORAGE/";
	
	@Mock
	Environment env;
	
	private StorageServiceImpl unit;
	
	@Before
	public void prepare() {
		MockitoAnnotations.initMocks(this);
		
		Mockito.when(env.getProperty("storage.path")).thenReturn(STORAGE_PATH);
		
		unit = new StorageServiceImpl(env);
	}
	
	@Test
	public void testGetUserStoragePath() {
		Assert.assertEquals( STORAGE_PATH + "123", unit.getUserStoragePath(123));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetUserStoragePathNull() {
		unit.getUserStoragePath(null);
	}

}
