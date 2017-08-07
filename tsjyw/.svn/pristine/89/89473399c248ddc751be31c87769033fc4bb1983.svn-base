package edu.iasd.security;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import edu.iasd.form.AdminMenuVO;
import edu.iasd.service.PrivilegeService;


public class CustomLoginHandler extends	SavedRequestAwareAuthenticationSuccessHandler {
	private PrivilegeService privilegeService;
	
	
	public void setPrivilegeService(PrivilegeService privilegeService) {
		this.privilegeService = privilegeService;
	}

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest request,
		HttpServletResponse response, 
		Authentication authentication) throws ServletException, IOException {		
		super.onAuthenticationSuccess(request, response, authentication);
		UserDetails userDetails = (UserDetails)authentication.getPrincipal();
		UserInfo ui = (UserInfo)userDetails;
		List<AdminMenuVO> menu = privilegeService.getAdminMenuByUser(ui.getViewUser().getUserId());

		
		request.getSession().setAttribute("adminMenu", menu);
		request.getSession().setAttribute("userId", ui.getViewUser().getUserId());
	}
}
