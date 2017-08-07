package edu.iasd.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.context.SecurityContextImpl;

import edu.iasd.security.UserInfo;
import edu.iasd.security.NotLoginException;

public class ControllerBase {
	protected UserInfo getCurrentUser(HttpServletRequest request) throws NotLoginException{
		SecurityContextImpl securityContextImpl = (SecurityContextImpl)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
		if (securityContextImpl == null)
			throw new NotLoginException();
		if (securityContextImpl.getAuthentication() == null || securityContextImpl.getAuthentication().getPrincipal() == null)
			throw new NotLoginException();
		if (securityContextImpl.getAuthentication().getPrincipal() instanceof UserInfo)
			return (UserInfo)securityContextImpl.getAuthentication().getPrincipal();
		else
			throw new NotLoginException();
	}
}
