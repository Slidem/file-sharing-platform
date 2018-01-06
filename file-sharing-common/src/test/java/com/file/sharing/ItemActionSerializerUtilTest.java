package com.file.sharing;

import java.time.Instant;

import org.junit.Assert;
import org.junit.Test;

import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.common.exceptions.ItemActionSerializationException;
import com.file.sharing.common.serializer.ItemActionSerializerUtil;


public class ItemActionSerializerUtilTest {

	private static final String DUMMY_ITEM_NAME = "dummyItemName";

	private static final String DUMMY_PATH_NAME = "dummyPathName";

	private static final Integer DUMMY_ID = 1;

	private static final Instant DUMMY_ACTION_TIME = Instant.now();

	// dummyItemName_dummyPathName_1_${Intant.now}
	private static final String DUMMY_SERIALIZED_ITEM_ACTION = "ZHVtbXlJdGVtTmFtZV9kdW1teVBhdGhOYW1lXzFfMTUxNTIxNTcyNjYyNw=="; // ->

	// dummy_1 -> only 2 parts
	private static final String INVALID_SERIALIZED_ITEM_ACTION_1 = "ZHVtbXlfMQ==";

	// dummy_dummy_1_notALongValue -> actionTime is not a long value
	private static final String INVALID_SERIALIZED_ITEM_ACTION_2 = "ZHVtbXlJdGVtTmFtZV9kdW1teVBhdGhOYW1lXzFfbm90QUxvbmdWYWx1ZQ==";


	private static final String INVALID_BASE64_SERIALIZED_STRING = "notABase64String";

	@Test
	public void test_successfull_serialization() {

		ItemActionDTO dto = new ItemActionDTO.Builder().withItemName(DUMMY_ITEM_NAME).withPath(DUMMY_PATH_NAME)
				.withUserId(DUMMY_ID).withActionTime(DUMMY_ACTION_TIME).build();
		String result = ItemActionSerializerUtil.serialize(dto);

		Assert.assertNotNull(result);
		Assert.assertFalse(result.isEmpty());
	}

	@Test
	public void test_successfull_deserialization() {
		ItemActionDTO dto = ItemActionSerializerUtil.deserialize(DUMMY_SERIALIZED_ITEM_ACTION);

		Assert.assertNotNull(dto);
		Assert.assertEquals(DUMMY_ITEM_NAME, dto.getItemName());
		Assert.assertEquals(DUMMY_PATH_NAME, dto.getPath());
		Assert.assertEquals(DUMMY_ID, dto.getUserId());
	}

	@Test(expected = ItemActionSerializationException.class)
	public void test_deserialization_fails_when_string_not_valid_1() {
		testDeserializationFailureWhenStringInvalid(INVALID_SERIALIZED_ITEM_ACTION_1);
	}

	@Test(expected = ItemActionSerializationException.class)
	public void test_deserialization_fails_when_string_not_valid_2() {
		testDeserializationFailureWhenStringInvalid(INVALID_SERIALIZED_ITEM_ACTION_2);
	}

	@Test(expected = ItemActionSerializationException.class)
	public void test_deserialization_fails_when_string_not_a_valid_base64_encoded_string() {
		testDeserializationFailureWhenStringInvalid(INVALID_BASE64_SERIALIZED_STRING);
	}

	private void testDeserializationFailureWhenStringInvalid(String invalidString) {
		// WHEN
		ItemActionSerializerUtil.deserialize(invalidString);
		// EXPECT: ItemActionSerializationException to be thrown
	}

}
