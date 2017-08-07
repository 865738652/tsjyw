package edu.iasd.security;  

import java.util.ArrayList;  
import java.util.Collection;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;  

import org.springframework.security.access.ConfigAttribute;  
import org.springframework.security.access.SecurityConfig;  
import org.springframework.security.web.FilterInvocation;  
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;  
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;  
import org.springframework.security.web.util.matcher.RequestMatcher;  

/** *//**  
*  
* 此类在初始化时，应该取到所有资源及其对应角色的定义 
*  
* @author Robin 
*  
*/   
public class MyInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {  
	private static Map <String, Collection <ConfigAttribute>> resourceMap= null ;  

	public MyInvocationSecurityMetadataSource() {  
		loadResourceDefine();  
	}  

	private void loadResourceDefine() {  
		resourceMap= new HashMap <String, Collection <ConfigAttribute>> ();  
		Collection <ConfigAttribute> atts= new ArrayList <ConfigAttribute> ();  
		ConfigAttribute ca = new SecurityConfig("ROLE_USER");  
		atts.add(ca);  
		resourceMap.put( "/ApplynManage/**" , atts); 
		resourceMap.put( "/AskQuestionManage/**" , atts); 
		resourceMap.put( "/ClassMaster/**" , atts); 
		resourceMap.put( "/CountyManage/**" , atts); 
		resourceMap.put( "/CountyPeople/**" , atts); 
		resourceMap.put( "/CourseManage/**" , atts); 
		resourceMap.put( "/FamousTeacherManage/**" , atts);
		resourceMap.put( "/GradeManage/**" , atts);
		resourceMap.put( "/GradeMaster/**" , atts);
		resourceMap.put( "/Admin/**" , atts);
		resourceMap.put( "/ModuleManage/**" , atts);
		resourceMap.put( "/SchoolClassManage/**" , atts);
		resourceMap.put( "/SchoolManage/**" , atts); 
		resourceMap.put( "/SchoolPeople/**" , atts);
		resourceMap.put( "/StudentManage/**" , atts);
		resourceMap.put( "/TeacherManage/**" , atts);
		resourceMap.put( "/uploadFile/**" , atts);
		resourceMap.put( "/UserManage/**" , atts);
		resourceMap.put( "/VolunteerManage/**" , atts);
		resourceMap.put( "/BusinessManage/**", atts);
	}  

	// According to a URL, Find out permission configuration of this URL.   
	public Collection <ConfigAttribute> getAttributes(Object object)  throws IllegalArgumentException {  
		// guess object is a URL.   
		FilterInvocation filterInvocation = (FilterInvocation) object; 
		Iterator <String> ite= resourceMap.keySet().iterator();  
		while (ite.hasNext()) {  
			String resURL= ite.next(); 
			RequestMatcher urlMatcher= new AntPathRequestMatcher(resURL);;  
			if (urlMatcher.matches(filterInvocation.getHttpRequest())) {  
				return resourceMap.get(resURL);  
			}  
		}  
		return null ;  
	}  

	public boolean supports(Class <?> clazz) {  
		return true ;  
	}  

	public Collection <ConfigAttribute> getAllConfigAttributes() {  
		return null ;  
	}  

}  
