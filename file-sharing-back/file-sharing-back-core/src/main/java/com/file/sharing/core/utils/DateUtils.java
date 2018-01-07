package com.file.sharing.core.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author Alexandru Mihai
 * @created Jan 7, 2018
 * 
 */
public final class DateUtils {

	private DateUtils() {
	}

	/**
	 * @param instant
	 * @return LocalDateTime representation using the system default time zone
	 */
	public static LocalDateTime fromInstant(Instant instant) {
		return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
	}

}
