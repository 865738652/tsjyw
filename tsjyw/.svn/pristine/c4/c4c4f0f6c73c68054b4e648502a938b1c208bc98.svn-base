package edu.iasd.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.iasd.pojo.AdminMenu;

public class AdminMenuVO implements Serializable, Comparable<AdminMenuVO> {
	private static final long serialVersionUID = 313651605960446668L;
	
	private Integer adminMenuId;
	private String adminMenuName;
	private String adminMenuUrl;
	private Integer adminMenuIndex;
	private List<AdminMenuVO> children;
	
	public Integer getAdminMenuId() {
		return adminMenuId;
	}

	public void setAdminMenuId(Integer adminMenuId) {
		this.adminMenuId = adminMenuId;
	}

	public String getAdminMenuName() {
		return adminMenuName;
	}

	public void setAdminMenuName(String adminMenuName) {
		this.adminMenuName = adminMenuName;
	}

	public String getAdminMenuUrl() {
		return adminMenuUrl;
	}

	public void setAdminMenuUrl(String adminMenuUrl) {
		this.adminMenuUrl = adminMenuUrl;
	}

	public Integer getAdminMenuIndex() {
		return adminMenuIndex;
	}

	public void setAdminMenuIndex(Integer adminMenuIndex) {
		this.adminMenuIndex = adminMenuIndex;
	}

	public List<AdminMenuVO> getChildren() {
		return children;
	}

	public void setChildren(List<AdminMenuVO> children) {
		this.children = children;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AdminMenuVO() {
		children = new ArrayList<AdminMenuVO>();
	}
	public AdminMenuVO(Integer id, String name, String url, Integer index) {
		children = new ArrayList<AdminMenuVO>();
		this.adminMenuId = id;
		this.adminMenuName = name;
		this.adminMenuUrl = url;
		this.adminMenuIndex = index;
	}
	
	public AdminMenuVO(AdminMenu menu) {
		children = new ArrayList<AdminMenuVO>();
		this.adminMenuId = menu.getAdminMenuId();
		this.adminMenuName = menu.getAdminMenuName();
		this.adminMenuUrl = menu.getAdminMenuUrl();
		this.adminMenuIndex = menu.getAdminMenuIndex();
		
		if (menu.getChildren() == null)
			return;
		
		for (AdminMenu m : menu.getChildren())
			addChild(new AdminMenuVO(m));
	}
	
	public void addChild(AdminMenuVO child) {
		for (AdminMenuVO c : children)
			if (c.adminMenuId == child.getAdminMenuId())
				return;
		children.add(child);
	}
	
	public int compareTo(AdminMenuVO o) {    
		AdminMenuVO s=(AdminMenuVO)o;     
		if(this.getAdminMenuIndex() > s.getAdminMenuIndex()) {      
			return 1;     
		} 
		if(this.getAdminMenuIndex() == s.getAdminMenuIndex()) {      
			return 0;     
		}  
		else { 
			return -1;     
		}       
	}     
}
