package edu.iasd.utils;

import java.util.HashSet;
import java.util.List;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;

public class UserHelper {
	public static void saveUser(IBaseDao<edu.iasd.pojo.User> userDao, User user) throws Exception {
		if (user.getUserAccount() != null && user.getUserAccount().length() > 0) {
			List<User> l = userDao.findBy("userAccount", user.getUserAccount());
			if (l != null && l.size() > 0) {
				if (l.size() > 1)
					throw new Exception("µÇÂ¼ÕË»§ÖØ¸´");
				else if (l.get(0).getUserId() != user.getUserId())
					throw new Exception("µÇÂ¼ÕË»§ÖØ¸´");
			}
		}
		userDao.save(user);
		String code = String.format("%08d", user.getUserId());
		user.setUserCode(code);
		userDao.save(user);
	}
	
	public static Boolean addRole(IBaseDao<edu.iasd.pojo.User> userDao, User user, 
			IBaseDao<edu.iasd.pojo.Role> roleDao, Integer roleId) {
		Role role = roleDao.findUniqueBy("roleId", roleId);
		if (user.getUserRoles() == null || !user.getUserRoles().contains(role)) {
			if (user.getUserRoles() == null)
				user.setUserRoles(new HashSet());
			user.getUserRoles().add(role);
			userDao.save(user);
			return true;
		}
		return false;
	}
	
	public static Boolean removeRole(IBaseDao<edu.iasd.pojo.User> userDao, User user, 
			IBaseDao<edu.iasd.pojo.Role> roleDao, Integer roleId) {
		Role role = roleDao.findUniqueBy("roleId", roleId);
		if (user.getUserRoles() != null && user.getUserRoles().contains(role)) {
			user.getUserRoles().remove(role);
			return true;
		}
		return false;
	}
	
	public static Boolean hasRole(IBaseDao<edu.iasd.pojo.User> userDao, int userId, 
			IBaseDao<edu.iasd.pojo.Role> roleDao, int roleId) {
		Role role = roleDao.findUniqueBy("roleId", roleId);
		User user = userDao.findUniqueBy("userId", userId);
		if (user.getUserRoles() != null && user.getUserRoles().contains(role)) 
			return true;
		else
			return false;
	}
}
