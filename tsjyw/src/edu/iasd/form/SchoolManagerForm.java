package edu.iasd.form;

import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolManager;
import edu.iasd.pojo.User;

public class SchoolManagerForm extends UserForm{
	private Integer schoolId;
	private Integer UserId;
	
	
	
	public Integer getSchoolId() {
		return schoolId;
	}



	public void setSchoolId(Integer schoolId) {
		this.schoolId = schoolId;
	}



	public Integer getUserId() {
		return UserId;
	}



	public void setUserId(Integer userId) {
		UserId = userId;
	}



	public void fillSchoolManager(SchoolManager s) {
		s.setUserId(getUserId());
	}
}
