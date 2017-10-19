package com.file.sharing;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import com.file.sharing.objects.AuthorizationInfo;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorizationInfoTest {

	private static final String EXPECTED_QUERY_STRING = "client_id=CL1&redirect_uri=http://localhost:8084/file-sharing/login&response_type=code&scope=filesharing&state=5ljONP";

	private static final Map<String, String[]> DUMMY_PARAMETERS = new HashMap<>();

	static {
		
		DUMMY_PARAMETERS.put("client_id", new String[] { "CL1" });
		DUMMY_PARAMETERS.put("redirect_uri", new String[] { "http://localhost:8084/file-sharing/login" });
		DUMMY_PARAMETERS.put("response_type", new String[] { "code" });
		DUMMY_PARAMETERS.put("scope", new String[] { "filesharing" });
		DUMMY_PARAMETERS.put("state", new String[] { "5ljONP" });
		
	}

	@Test
	public void testQueryString() {
		
		// GIVEN
		AuthorizationInfo unit = new AuthorizationInfo(DUMMY_PARAMETERS);

		// EXPECT
		assertEquals(EXPECTED_QUERY_STRING, unit.getQueryParams());
		
	}

}
