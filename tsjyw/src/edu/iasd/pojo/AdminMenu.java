package edu.iasd.pojo;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

public class AdminMenu implements java.io.Serializable{
	private static final long serialVersionUID = -7517893944336932625L;
	private Integer adminMenuId;
	private String adminMenuName;
	private String adminMenuUrl;
	private Integer adminMenuIndex;
    private AdminMenu parent;
	private Set<AdminMenu> children;

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

	public AdminMenu getParent() {
		return parent;
	}

	public void setParent(AdminMenu parent) {
		this.parent = parent;
	}

	public Set<AdminMenu> getChildren() {
		return children;
	}

	public void setChildren(Set<AdminMenu> children) {
		this.children = children;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Integer getAdminMenuIndex() {
		return adminMenuIndex;
	}

	public void setAdminMenuIndex(Integer adminMenuIndex) {
		this.adminMenuIndex = adminMenuIndex;
	}	
}
