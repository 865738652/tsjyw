package edu.iasd.security; 

import java.util.ArrayList;  
import java.util.Collection;  

import org.springframework.dao.DataAccessException;  
import org.springframework.security.core.GrantedAuthority;  
import org.springframework.security.core.authority.SimpleGrantedAuthority;  
import org.springframework.security.core.userdetails.UserDetails;  
import org.springframework.security.core.userdetails.UserDetailsService;  
import org.springframework.security.core.userdetails.UsernameNotFoundException; 

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.ViewUser;
import edu.iasd.utils.CreditHelper;

public class MyUserDetailService implements UserDetailsService {  

	private IBaseDao<edu.iasd.pojo.ViewUser> viewUserDao;
	
	public void setViewUserDao(IBaseDao<edu.iasd.pojo.ViewUser> viewUserDao) {
		this.viewUserDao = viewUserDao;
	}
	
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	public void setUserDao(IBaseDao<edu.iasd.pojo.User> userDao) {
		this.userDao = userDao;
	}

	public MyUserDetailService() {
	}
	
	@Override  
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {  
		ViewUser vu = viewUserDao.findUniqueBy("userCode", username);
		if (vu == null)
			vu = viewUserDao.findUniqueBy("userAccount", username);
		if (vu == null)
			throw new UsernameNotFoundException("ÓÃ»§Ãû´íÎó " + username);
		
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();  
		
		edu.iasd.pojo.User u = userDao.findUniqueBy("userId", vu.getUserId());
		if (u.getUserRoles() != null) {
			for (Object o : u.getUserRoles()) {
				edu.iasd.pojo.Role r = (edu.iasd.pojo.Role)o;
				SimpleGrantedAuthority auth = new SimpleGrantedAuthority(r.getRoleName());
				auths.add(auth);
			}
		}
		
		// User(String username, String password, boolean enabled, boolean accountNonExpired,  
		// boolean credentialsNonExpired, boolean accountNonLocked, Collection<GrantedAuthority> authorities) {  
		UserInfo user=new UserInfo(username, vu.getUserPassword(), true, true, true, true, auths, vu);  
		
		
		return user;  
	} 
}  
