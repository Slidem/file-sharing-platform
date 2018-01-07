package com.file.sharing.rest.config;

import java.time.Instant;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.file.sharing.rest.FileSharingBackRestPackageScan;
import com.file.sharing.rest.serializer.InstantDeserializer;

/**
 * @author Alexandru
 *
 */
@Configuration
@ComponentScan(basePackageClasses = { FileSharingBackRestPackageScan.class })
public class FileSharingBackRestConfig {

	private static final Logger LOGGER = LoggerFactory.getLogger(FileSharingBackRestConfig.class);

	@PostConstruct
	public void init() {
		LOGGER.info("Added file sharing backend rest module");
	}

	@Autowired
	public void configMapper(ObjectMapper objectMapper) {
		objectMapper.registerModule(instantSerializationModule());
	}

	private SimpleModule instantSerializationModule() {
		SimpleModule module = new SimpleModule("InsantSerializer");
		module.addSerializer(Instant.class, new ToStringSerializer());
		module.addDeserializer(Instant.class, new InstantDeserializer());
		return module;
	}
}
