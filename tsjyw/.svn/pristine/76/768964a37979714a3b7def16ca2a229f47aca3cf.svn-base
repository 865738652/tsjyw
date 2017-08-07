package edu.iasd.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import edu.iasd.pojo.ViewUser;

public class UserInfo extends User {
	private static final long serialVersionUID = 1L; 
	
	private ViewUser viewUser;
	
	public ViewUser getViewUser() {
		return viewUser;
	}

	@SuppressWarnings("deprecation")  
    public UserInfo(String username, String password, boolean enabled, boolean accountNonExpired,  
    		boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities,
    		ViewUser viewUser) 
        throws IllegalArgumentException {  
        super(username,password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);  
        
        this.viewUser = viewUser;
    }  
}
