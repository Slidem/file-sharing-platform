package com.file.sharing.jms.commons.converter.impl;

import org.springframework.stereotype.Component;

import com.file.sharing.jms.commons.converter.JmsMessageConverter;
import com.google.gson.Gson;

/**
 * @author Alexandru Mihai
 * @created Oct 21, 2017
 */
@Component
public class JsonMessageConverter implements JmsMessageConverter {

	private final Gson gson;

	public JsonMessageConverter(Gson gson) {
		this.gson = gson;
	}

	@Override
	public String toString(Object object) {
		return gson.toJson(object);
	}

	@Override
	public <T> T toObject(String json, Class<T> type) {
		return gson.fromJson(json, type);
	}
}
