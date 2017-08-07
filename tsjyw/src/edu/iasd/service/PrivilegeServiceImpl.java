package edu.iasd.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demonstration.hibernate.basedao.IBaseDao;

import edu.iasd.form.AdminMenuVO;
import edu.iasd.pojo.AdminMenu;
import edu.iasd.pojo.Role;
import edu.iasd.pojo.User;

public class PrivilegeServiceImpl implements PrivilegeService {

	private IBaseDao<User> userDao;
	private IBaseDao<Role> roleDao; 
	private IBaseDao<AdminMenu> adminMenuDao;
	
	public void setUserDao(IBaseDao<User> userDao) {
		this.userDao = userDao;
	}

	public void setRoleDao(IBaseDao<Role> roleDao) {
		this.roleDao = roleDao;
	}

	public void setAdminMenuDao(IBaseDao<AdminMenu> adminMenuDao) {
		this.adminMenuDao = adminMenuDao;
	}

	@Override
	public List<AdminMenuVO> getAdminMenuByUser(Integer userId) {
		User user = userDao.findUniqueBy("userId", userId);
		List<AdminMenuVO> menu = new ArrayList<AdminMenuVO>();
		
		/*
		 * �����һ���˵�,��������˵�δ�����������˵��б��У��ٽ��������Ӳ˵������б�
		 */
		for (Object o : user.getUserRoles()) {
			Role r = (Role)o;
			Set<AdminMenu> cur = r.getAdminMenus();
			
			for (AdminMenu m : cur) {				
				if (m.getParent() == null) {					
					Boolean found = false;
					for (AdminMenuVO av : menu) {
						if (m.getAdminMenuId() == av.getAdminMenuId()) {
							found = true;
							break;
						}
					}
					if (found)
						continue;
					
					AdminMenuVO vo = new AdminMenuVO(m);
					menu.add(vo);
				}				
			}
			
		}
		
		/*
		 * �����Ӳ˵�
		 */
		for (Object o : user.getUserRoles()) {
			Role r = (Role)o;
			Set<AdminMenu> cur = r.getAdminMenus();
			
			for (AdminMenu m : cur) {
				if (m.getParent() != null)  {
					/*
					 * ���Ҹò˵��ĸ��˵��Ƿ������б���
					 */
					AdminMenuVO av = null;
					for (int i = 0; i < menu.size(); i++) {
						if (m.getParent().getAdminMenuId() == menu.get(i).getAdminMenuId()) {
							av = menu.get(i);
							break;
						}
					}
					
					if (av == null) {
						/*
						 * ������˵�û�����б��У�������룬������ǰ�Ӳ˵�Ҳ����
						 */
						av = new AdminMenuVO(m.getParent().getAdminMenuId(), m.getParent().getAdminMenuName(), m.getParent().getAdminMenuUrl(), m.getAdminMenuIndex());
						menu.add(av);
						av.addChild(new AdminMenuVO(m.getAdminMenuId(), m.getAdminMenuName(), m.getAdminMenuUrl(), m.getAdminMenuIndex()));
					}
					else {
						/*
						 * ������˵������б��У����жϵ�ǰ�˵��Ƿ��Ѽ����б���δ���룬�������
						 */
						Boolean found = false;
						for (AdminMenuVO sub : av.getChildren()) {
							if (sub.getAdminMenuId() == m.getAdminMenuId()) {
								found = true;
								break;
							}
						}
						if (!found) {
							av.addChild(new AdminMenuVO(m.getAdminMenuId(), m.getAdminMenuName(), m.getAdminMenuUrl(), m.getAdminMenuIndex()));
						}
					}
				}
			}
		}
		
		/*
		 * sort all menu and submenu
		 */
		Collections.sort(menu);
		for (AdminMenuVO v : menu) {
			Collections.sort(v.getChildren());
		}
			
		return menu;
	}

}
