package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * Role entity. @author MyEclipse Persistence Tools
 */

public class Role  implements java.io.Serializable {

	
	//jsp page use static path,RecvNotice.jsp only use this number,not name
	public final static int ROLE_SUPERADMIN 		= 1;
	public final static int ROLE_COUNTYADMIN 		= 2;
	public final static int ROLE_SCHOOLMASTER 		= 3;
	public final static int ROLE_SCHOOLADMIN 		= 4;
	public final static int ROLE_GRADEMASTER		= 5;
	public final static int ROLE_CLASSMASTER 		= 6;
	public final static int ROLE_COURSETEACHER		= 7;
	public final static int ROLE_SCHOOLTEACHER 		= 8;
	public final static int ROLE_VOLUNTEER 			= 9;
	public final static int ROLE_FAMOUSTEACHER 		= 10;
	public final static int ROLE_BUSINESSADMIN 		= 11;
	public final static int ROLE_STUDENT	 		= 12;
	public final static int ROLE_USER		 		= 100;
	
    // Fields    

     private Integer roleId;
     private String roleName;
     private Set users = new HashSet(0);
     private Set adminMenus = new HashSet(0);


    // Constructors

    /** default constructor */
    public Role() {
    }

	/** minimal constructor */
    public Role(String roleName) {
        this.roleName = roleName;
    }

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set getUsers() {
		return users;
	}

	public void setUsers(Set users) {
		this.users = users;
	}

	public Set getAdminMenus() {
		return adminMenus;
	}

	public void setAdminMenus(Set adminMenus) {
		this.adminMenus = adminMenus;
	}
    
    /** full constructor */
    

   
    // Property accessors

    
   








}