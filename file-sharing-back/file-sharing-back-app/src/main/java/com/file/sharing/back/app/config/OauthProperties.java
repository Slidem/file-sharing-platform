package com.file.sharing.back.app.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Repository;

/**
 * @author slidem
 *
 */
@Repository
@ConfigurationProperties(prefix = "oauth")
public class OauthProperties {

	private String checkTokenUrl;

	private String clientId;

	private String clientSecret;

	public String getCheckTokenUrl() {
		return checkTokenUrl;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

}
