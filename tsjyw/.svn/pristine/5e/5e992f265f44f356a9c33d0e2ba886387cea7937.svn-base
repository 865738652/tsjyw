package edu.iasd.pojo;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_user")
public class ViewUser extends ViewUserBase implements java.lang.Comparable{
	private static final long serialVersionUID = -7674269980281525370L;

	@Override
	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		ViewUser p = (ViewUser)o;
		return p.getUserId().intValue() - this.getUserId().intValue();
	}
	
	
	
}
