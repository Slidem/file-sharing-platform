package com.file.sharing.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.file.sharing.constants.FileSharingAuthConstants;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@Component
public class LoginAuthorizationFilter extends BaseAuthorizationFilter {

	@Autowired
	public LoginAuthorizationFilter(Environment environment) {
		super(environment);
	}

	@Override
	protected void processRequest(HttpServletRequest request, String httpMethod, String endpoint) {
		if ("POST".equals(httpMethod) && "/login".equals(endpoint)) {
			request.getSession().removeAttribute(FileSharingAuthConstants.AUTH_INFO_ATTR_NAME);
		}
	}

}
