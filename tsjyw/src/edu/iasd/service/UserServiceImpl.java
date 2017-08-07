package edu.iasd.service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baidu.ueditor.PathFormat;
import com.demonstration.hibernate.basedao.IBaseDao;
import com.demonstration.hibernate.dao.support.Page;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import edu.iasd.form.UserForm;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;
import edu.iasd.pojo.ViewUser;
import edu.iasd.utils.CreditHelper;
import edu.iasd.utils.MatrixToImageWriter;
import edu.iasd.utils.UserHelper;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Resource
	private IBaseDao<edu.iasd.pojo.User> userDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.ViewUser> viewUserDao;
	
	@Resource
	private IBaseDao<edu.iasd.pojo.Role> roleDao;
	
	@Override
	public Page getAllUser(String queryString, int pageNow, int pageSize) {
		String where = (queryString != null && !queryString.equals("")) ? " where " + queryString : "";
		return viewUserDao.pagedQuery("from ViewUser u " + where, pageNow, pageSize);
	}

	@Override
	public ViewUser getUser(int userId) {
		return viewUserDao.findUniqueBy("userId", userId);
	}

	@Override
	public String createUser(UserForm form) throws Exception{
		User user = new User();
		form.fillUser(user);
		user.setUserIntegral(10);
		UserHelper.saveUser(userDao, user);
		UserHelper.addRole(userDao, user, roleDao, Role.ROLE_USER);
		return user.getUserId().toString();
	}

	@Override
	public Boolean deleteUser(int userId) {
		/*
		 * 删除角色
		 */
		User u = userDao.findUniqueBy("userId", userId);
		if (u.getUserRoles() != null) {
			u.getUserRoles().clear();
			userDao.save(u);
		}
		userDao.removeById(userId);
		return true;
	}

	@Override
	public Boolean modifyUser(UserForm form) throws Exception {
		User user = userDao.findUniqueBy("userId", form.getUserId());
		if (user == null)
			return false;		
		form.fillUser(user);
		userDao.save(user);
		return true;
	}

	@Override
	public Boolean changePassword(int userId, String newPassword) throws Exception{
		User user = userDao.findUniqueBy("userId", userId);
		if (user == null)
			return false;
		user.setUserPassword(newPassword);
		userDao.save(user);
		return true;
	}
	
	@Override
	public Boolean changePassword(int userId, String oldPassword, String newPassword) throws Exception {
		User user = userDao.findUniqueBy("userId", userId);
		if (user == null)
			return false;
		if (!user.getUserPassword().equals(oldPassword))
			throw new Exception("原密码错误");
		user.setUserPassword(newPassword);
		userDao.save(user);
		return true;
	}
	
	@Override
	public Boolean changePhoto(int userId, String photoUrl) throws Exception {
		User user = userDao.findUniqueBy("userId", userId);
		if (user == null)
			return false;
		user.setUserPhotoUrl(photoUrl);
		userDao.save(user);
		return true;
	}

	//获取用户的全部角色
	@Override
	public Set<Integer> getUserRoles(int userId) {
		// TODO Auto-generated method stub
		
		User user = userDao.findUniqueBy("userId",userId);
		Set<Role> roles = user.getUserRoles();
		
		Set<Integer> roleIds = new HashSet<Integer>();
		
		for(Role role : roles)
		{
			Integer roleId = role.getRoleId();
			roleIds.add(roleId);
		}
		return roleIds;
	}

	@Override
	public void saveWeQRCode(String rootPath, int userId)
	{
		try {
			User user = userDao.findUniqueBy("userId", userId);
			if (user == null || user.getUserWeixin() == null)
				return;
			
			String path = null, url = null;
			if (user.getUserWeQRCodeUrl() != null) {
				url = user.getUserWeQRCodeUrl();
				if (url.startsWith("../upload/")) {
					path = url.replaceFirst("../upload/", "WEB-INF/upload/");
					path = String.format("%s%s", rootPath, path);
				}
			}
			else {
				Date date = new Date();
			    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
				path = String.format("%sWEB-INF/upload/%s/", rootPath, formatter.format(date));
				url = String.format("../upload/%s/", formatter.format(date));
				File file = new File(path);	
				if  (!file.exists() && !file.isDirectory())      
					file .mkdir();    
				
				long times = date.getTime();
				String rand = (Math.random() + "").replace(".", "").substring(0, 6);
				path = String.format("%s%d%s.png", path, times, rand);
				url = String.format("%s%d%s.png", url, times, rand);
			}
			
			if (path == null)
				return;
			
		    String content = "http://weixin.qq.com/r/60Ovt7bEEeturfLU9xb5 ";//user.getUserWeixin();		     
		    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();		     
		    Map hints = new HashMap();
		    hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
		    BitMatrix bitMatrix = multiFormatWriter.encode(content, BarcodeFormat.QR_CODE, 400, 400,hints);
		    File file1 = new File(path);
		    MatrixToImageWriter.writeToFile(bitMatrix, "png", file1);
		    
		    user.setUserWeQRCodeUrl(url);
		    userDao.save(user);
		 } catch (Exception e) {
		     e.printStackTrace();
		 }
	}
	
	public boolean hasRole(int userId, int roleId) {
		return UserHelper.hasRole(userDao, userId, roleDao, roleId);
	}

	@Override
	public ViewUser getUserByAccount(String userAccount) {
		ViewUser vu=viewUserDao.findUniqueBy("userAccount", userAccount);

		return vu;
		
	}

	@Override
	public int getUserIntegralRank(int userId) throws Exception{
		// TODO Auto-generated method stub
		List<ViewUser> users = userDao.find("from ViewUser v order by v.userIntegral",null);
		return Collections.binarySearch(users, viewUserDao.get(userId), null);
	}

	@Override
	public void addUserIntegral(Integer userId) {
		// TODO Auto-generated method stub
		CreditHelper.trigger(userDao, userId, CreditHelper.CREDIT_TYPE_LOGIN);
	}

//	@Override
//	public ViewUser findUserByName(String userName) {
//		//User u=viewUserDao.find("from ViewUser where userName=?",userName);
//		return viewUserDao.findUniqueBy("userName", userName);
//		
//	}

}
