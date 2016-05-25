package com.xxx.demo.filter;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.jasig.cas.client.authentication.AuthenticationFilter;
import org.jasig.cas.client.validation.Assertion;

public class AutoSetUserAdapterFilter implements Filter {
	/**
	 * Default constructor.
	 */
	public AutoSetUserAdapterFilter() {
		System.out.println("[AutoSetUserAdapterFilter]");
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		// _const_cas_assertion_是CAS中存放登录用户名的session标志
		Object object = httpRequest.getSession().getAttribute(
				AuthenticationFilter.CONST_CAS_ASSERTION);

		if (object != null) {
			Assertion assertion = (Assertion) object;
			String loginName = assertion.getPrincipal().getName();
			System.out.println("[loginname]: " + loginName);

//			Map<String, Object> map = assertion.getPrincipal().getAttributes();
//			String email = (String) map.get("email");
//			String name = (String) map.get("name");
//			String username = (String) map.get("username");
//			System.out.println("[email]: " + email);
//			System.out.println("[name]: " + name);
//			System.out.println("[username]: " + username);
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}
}
