package edu.iasd.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.dao.support.Page;

import edu.iasd.form.UserForm;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.ViewUser;

@Service
@Transactional
public interface UserService {
	public Page getAllUser(String queryString, int pageNow, int pageSize);
		
	public ViewUser getUser(int userId);
	
	public String createUser(UserForm form) throws Exception;
	
	public Boolean deleteUser(int userId);
	
	public Boolean modifyUser(UserForm form) throws Exception;
	
	public Boolean changePassword(int userId, String newPassword) throws Exception;
	
	public Boolean changePassword(int userId, String oldPassword, String newPassword) throws Exception;
	
	public Boolean changePhoto(int userId, String photoUrl) throws Exception;	
	
	public Set<Integer> getUserRoles(int userId); 
	
	public void saveWeQRCode(String rootPath, int userId);
	
	public boolean hasRole(int userId, int roleId);
	
	public ViewUser getUserByAccount(String userAccount);
	
	public int getUserIntegralRank(int userId) throws Exception;
	
	public void addUserIntegral(Integer userId);
	
}