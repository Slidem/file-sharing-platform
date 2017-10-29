package com.file.sharing.objects;

import java.io.Serializable;
import java.util.Map;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
public final class AuthorizationInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4152191633068111919L;

	// parameters keys
	public static final String REDIRECT_URI_PARAM = "redirect_uri";

	public static final String CLIENT_ID_PARAM = "client_id";

	public static final String RESPONSE_TYPE_PARAM = "response_type";

	public static final String SCOPE_PARAM = "scope";

	public static final String STATE_PARAM = "state";
	// ------------------------------------------------

	private final String redirectUri;

	private final String clientId;

	private final String responseType;

	private final String scope;

	private final String state;

	private String queryParams;


	public AuthorizationInfo(final Map<String, String[]> requestParameters) {
		this.redirectUri = getIfPresent(requestParameters.get(REDIRECT_URI_PARAM));
		this.clientId = getIfPresent(requestParameters.get(CLIENT_ID_PARAM));
		this.responseType = getIfPresent(requestParameters.get(RESPONSE_TYPE_PARAM));
		this.scope = getIfPresent(requestParameters.get(SCOPE_PARAM));
		this.state = getIfPresent(requestParameters.get(STATE_PARAM));
		this.queryParams = getAsQueryParamString();
	}

	public String getRedirectUri() {
		return redirectUri;
	}

	public String getClientId() {
		return clientId;
	}

	public String getResponseType() {
		return responseType;
	}

	public String getScope() {
		return scope;
	}

	public String getState() {
		return state;
	}

	public String getQueryParams() {
		return queryParams;
	}

	public void setQueryParams(String queryParams) {
		this.queryParams = queryParams;
	}

	private String getIfPresent(String[] parts) {
		return parts == null ? "" : parts[0];
	}

	private String getAsQueryParamString() {
		StringBuilder fullUrl = new StringBuilder();
		addQueryParam(fullUrl, "?" + CLIENT_ID_PARAM, this.clientId);
		addQueryParam(fullUrl, "&" + REDIRECT_URI_PARAM, this.redirectUri);
		addQueryParam(fullUrl, "&" + RESPONSE_TYPE_PARAM, this.responseType);
		addQueryParam(fullUrl, "&" + SCOPE_PARAM, this.scope);
		addQueryParam(fullUrl, "&" + STATE_PARAM, this.state);
		return fullUrl.toString();
	}

	private void addQueryParam(StringBuilder sb, String paramName, String paramValue) {
		sb.append(paramName).append("=").append(paramValue);
	}

}
