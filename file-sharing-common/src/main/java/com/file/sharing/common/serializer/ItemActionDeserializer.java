package com.file.sharing.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.file.sharing.common.dto.ItemActionDTO;
import com.file.sharing.common.serializer.ItemActionSerializerUtil;

/**
 * @author Alexandru Mihai
 * @created Dec 29, 2017
 * 
 */
public class ItemActionDeserializer extends StdDeserializer<ItemActionDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4043644054269826397L;

	public ItemActionDeserializer() {
		this(null);
	}

	public ItemActionDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public ItemActionDTO deserialize(JsonParser jp, DeserializationContext context)
			throws IOException, JsonProcessingException {

		JsonNode node = jp.getCodec().readTree(jp);
		String itemActionId = node.get("itemActionId").asText();

		return ItemActionSerializerUtil.deserialize(itemActionId);
	}

}
