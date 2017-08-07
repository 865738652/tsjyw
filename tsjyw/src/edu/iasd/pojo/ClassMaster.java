package edu.iasd.pojo;


public class ClassMaster implements java.io.Serializable {
	 private Integer classMasterId;
     private SchoolClass schoolClass;
     private Teacher teacher;
     
     /** default constructor */
     public ClassMaster() {
     }
     
     /** full constructor */
     public ClassMaster(SchoolClass schoolClass, Teacher teacher) {
         this.schoolClass = schoolClass;
         this.teacher = teacher;
     }
     

	public Integer getClassMasterId() {
		return classMasterId;
	}

	public void setClassMasterId(Integer classMasterId) {
		this.classMasterId = classMasterId;
	}

	public SchoolClass getSchoolClass() {
		return schoolClass;
	}
	public void setSchoolClass(SchoolClass schoolClass) {
		this.schoolClass = schoolClass;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
     
     

}
