package edu.iasd.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class UEditorUploadFilter implements  javax.servlet.Filter {

	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		HttpServletRequest req = (HttpServletRequest) request; 
		if (req.getHeader("X-CSRF-TOKEN") == null) {
			MutableHttpServletRequest mutableRequest = new MutableHttpServletRequest(req);  
			mutableRequest.putHeader("X-CSRF-TOKEN", "custom value");
			request = mutableRequest;
		}
        filterChain.doFilter(request, response); 
	}
	
	@Override  
    public void init(FilterConfig filterConfig) throws ServletException {  
          
    }  
	
	@Override  
    public void destroy() {  
          
    }  
}
