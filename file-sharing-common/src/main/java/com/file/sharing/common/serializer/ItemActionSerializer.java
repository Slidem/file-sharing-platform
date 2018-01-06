package com.file.sharing.common.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.file.sharing.common.dto.ItemActionDTO;

/**
 * @author Alexandru Mihai
 * @created Dec 29, 2017
 * 
 */
public class ItemActionSerializer extends StdSerializer<ItemActionDTO> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6770020641265955394L;

	public ItemActionSerializer() {
		this(ItemActionDTO.class);
	}

	public ItemActionSerializer(Class<ItemActionDTO> t) {
		super(t);
	}

	@Override
	public void serialize(ItemActionDTO itemActionDTO, JsonGenerator jgen, SerializerProvider provider)
			throws IOException {
		jgen.writeString(ItemActionSerializerUtil.serialize(itemActionDTO));
	}

	@Override
	public Class<ItemActionDTO> handledType() {
		return ItemActionDTO.class;
	}

}
