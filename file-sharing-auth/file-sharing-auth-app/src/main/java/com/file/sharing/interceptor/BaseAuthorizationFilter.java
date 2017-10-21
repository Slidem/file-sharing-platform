package com.file.sharing.interceptor;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.env.Environment;
import org.springframework.web.filter.GenericFilterBean;

/**
 * @author Alexandru Mihai
 * @created Oct 19, 2017
 */
public abstract class BaseAuthorizationFilter extends GenericFilterBean {

	private final String contextPath;

	protected BaseAuthorizationFilter(Environment environment) {
		contextPath = environment.getProperty("server.contextPath");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		String httpMethod = request.getMethod();
		String[] endpointParts = request.getRequestURL().toString().split(contextPath);
		String pathAfterContext = endpointParts[1];

		processRequest(request, httpMethod, pathAfterContext);
		
		chain.doFilter(req, res);

	}

	protected abstract void processRequest(HttpServletRequest request, String httpMethod, String endpoint);

}
