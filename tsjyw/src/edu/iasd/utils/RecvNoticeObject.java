package edu.iasd.utils;

import java.util.List;

import edu.iasd.pojo.Grade;
import edu.iasd.pojo.School;
import edu.iasd.pojo.SchoolClass;

public class RecvNoticeObject {
	
	private List<SchoolClass> schoolClass;
	private List<Grade> grade;
	private List<School> school;
	public List<SchoolClass> getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(List<SchoolClass> schoolClass) {
		this.schoolClass = schoolClass;
	}
	public List<Grade> getGrade() {
		return grade;
	}
	public void setGrade(List<Grade> grade) {
		this.grade = grade;
	}
	public List<School> getSchool() {
		return school;
	}
	public void setSchool(List<School> school) {
		this.school = school;
	}

}
