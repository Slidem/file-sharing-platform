package com.file.sharing.business;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import com.file.sharing.business.impl.UserDetailsBusinessImpl;
import com.file.sharing.dao.UserDAO;

@RunWith(MockitoJUnitRunner.class)
public class UserDetailsBusinessTest {

	@Mock
	private UserDAO userDao;

	private UserDetailsBusiness unit;

	@Before
	public void before() {
		MockitoAnnotations.initMocks(this);
		unit = new UserDetailsBusinessImpl(userDao);
	}

	@Test(expected = NullPointerException.class)
	public void test_getUserDetailsByBaseUser_fails_when_baseUser_is_null() {
		unit.getUserDetailsFromBaseUser(null);
	}

}
