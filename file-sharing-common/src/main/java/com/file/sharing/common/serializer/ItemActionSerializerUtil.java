package com.file.sharing.common.serializer;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.common.exceptions.ItemActionSerializationException;

/**
 * @author Alexandru Mihai
 * @created Dec 28, 2017
 * 
 */
public final class ItemActionSerializerUtil {

	private static final String ENCODED_STRING_FORMAT = "%s_%s_%d_%d";

	private static final Pattern ENCODED_STRING_PATTERN = Pattern.compile("(.*)_(.*)_([0-9]*)_([0-9]*)");

	private static final Charset ENCODE_CHARSET = StandardCharsets.UTF_8;

	private ItemActionSerializerUtil() {
	}

	/**
	 * @param itemActionDTO
	 * @return Encoded base64 String representation of itemActionDTO
	 */
	public static String serialize(ItemActionDTO itemActionDTO) {

		//@formatter:off
		String stringToBeEncoded = String.format(ENCODED_STRING_FORMAT, 
				                         itemActionDTO.getItemName(), 
				                         itemActionDTO.getPath(), 
				                         itemActionDTO.getUserId(),
				                         itemActionDTO.getActionTime().toEpochMilli());
		//@formatter:on
		return Base64.getEncoder().encodeToString(stringToBeEncoded.getBytes(ENCODE_CHARSET));
	}

	public static ItemActionDTO deserialize(String text) {
		Matcher matcher;

		try {
			byte[] decodedTextBytes = Base64.getDecoder().decode(text.getBytes(ENCODE_CHARSET));
			String decodedText = new String(decodedTextBytes, ENCODE_CHARSET);
			matcher = ENCODED_STRING_PATTERN.matcher(decodedText);
			//@formatter:on
		} catch (IllegalArgumentException e) {
			throw new ItemActionSerializationException(e);
		}

		if (!matcher.matches()) {
			throw new ItemActionSerializationException("Decoded text does not match pattern.");
		}

		String itemName = matcher.group(1);
		String path = matcher.group(2);
		Integer userId = Integer.valueOf(matcher.group(3));
		Long actionTime = Long.valueOf(matcher.group(4));

		//@formatter:off
		return new ItemActionDTO.Builder()
				.withItemName(itemName)
				.withPath(path)
				.withUserId(userId)
				.withActionTime(Instant.ofEpochMilli(actionTime))
				.build();
	}


}
