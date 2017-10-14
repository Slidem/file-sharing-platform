package com.file.sharing.back.app.config;

/**
 * @author slidem
 *
 */
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

	public void setCheckTokenUrl(String checkTokenUrl) {
		this.checkTokenUrl = checkTokenUrl;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	
	
	

}
