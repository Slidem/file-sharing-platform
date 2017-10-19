package com.file.sharing.interceptor;

import static com.file.sharing.constants.FileSharingAuthConstants.AUTH_INFO_ATTR_NAME;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.file.sharing.objects.AuthorizationInfo;

/**
 * @author Alexandru Mihai
 * @created Oct 15, 2017
 */
@Component
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class AuthorizationFilter extends BaseAuthorizationFilter {

	@Autowired
	public AuthorizationFilter(Environment environment) {
		super(environment);
	}

	@Override
	protected void processRequest(HttpServletRequest request, String httpMethod, String endpoint) {
		if ("GET".equals(httpMethod) && "/oauth/authorize".equals(endpoint)) {
			AuthorizationInfo authorizationInfo = new AuthorizationInfo(request.getParameterMap());
			request.getSession().setAttribute(AUTH_INFO_ATTR_NAME, authorizationInfo);
		}
	}

}
