package com.file.sharing.rest.serializer;

import java.io.IOException;
import java.time.Instant;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.FromStringDeserializer;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
public class InstantDeserializer extends FromStringDeserializer<Instant> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2463771253607406754L;

	public InstantDeserializer() {
		this(Instant.class);
	}

	protected InstantDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	protected Instant _deserialize(String instantAsString, DeserializationContext context) throws IOException {
		return Instant.parse(instantAsString);
	}

}
