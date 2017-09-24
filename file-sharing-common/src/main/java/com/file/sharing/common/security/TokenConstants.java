package com.file.sharing.common.security;

public interface TokenConstants {

	public static final String CLAIM_KEY_USERNAME = "sub";
	public static final String CLAIM_KEY_AUDIENCE = "audience";
	public static final String CLAIM_KEY_CREATED = "created";
	public static final String CLAIM_KEY_AUTHORITIES = "authorities";

	public static final String AUDIENCE_UNKNOWN = "unknown";
	public static final String AUDIENCE_WEB = "web";
	public static final String AUDIENCE_MOBILE = "mobile";
	public static final String AUDIENCE_TABLET = "tablet";

}
