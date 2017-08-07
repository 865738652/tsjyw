package edu.iasd.form;

public class ClassMasterForm extends TeacherForm{
	
	private static final long serialVersionUID = 455436837762002267L;
	private Integer classMasterId;
  	private Integer schoolClassId;
  	
	public Integer getClassMasterId() {
		return classMasterId;
	}
	public void setClassMasterId(Integer classMasterId) {
		this.classMasterId = classMasterId;
	}
	public Integer getSchoolClassId() {
		return schoolClassId;
	}
	public void setSchoolClassId(Integer schoolClassId) {
		this.schoolClassId = schoolClassId;
	}
  	
  	

}
