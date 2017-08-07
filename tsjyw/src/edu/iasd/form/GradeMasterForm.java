package edu.iasd.form;

public class GradeMasterForm extends TeacherForm{
  	private static final long serialVersionUID = 455436837762002267L;
	private Integer gradeMasterId;
  	private Integer gradeId;
  
	public Integer getGradeMasterId() {
		return gradeMasterId;
	}
	public void setGradeMasterId(Integer gradeMasterId) {
		this.gradeMasterId = gradeMasterId;
	}
	public Integer getGradeId() {
		return gradeId;
	}
	public void setGradeId(Integer gradeId) {
		this.gradeId = gradeId;
	}	
}
