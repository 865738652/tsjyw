package edu.iasd.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.util.matcher.RequestMatcher;

public class AjaxRequestMatcher implements RequestMatcher{

	@Override
	public boolean matches(HttpServletRequest request) {
		// TODO Auto-generated method stub
		
		return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
	}

}
