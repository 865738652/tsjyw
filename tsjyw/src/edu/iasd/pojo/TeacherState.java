package edu.iasd.pojo;
// default package

/**
 * TeacherState entity. @author MyEclipse Persistence Tools
 */

public class TeacherState  implements java.io.Serializable {

	private static final long serialVersionUID = -2675841143916303780L;
	public final static int TEACHER_STATE_NORMAL = 1;
	public final static int TEACHER_STATE_RETAIL = 2;
	
    // Fields    

     private Integer teacherStateId;
     private String teacherStateName;

    // Constructors

    /** default constructor */
    public TeacherState() {
    }

	/** minimal constructor */
    public TeacherState(String teacherStateName) {
        this.teacherStateName = teacherStateName;
    }
    
    // Property accessors

    public Integer getTeacherStateId() {
        return this.teacherStateId;
    }
    
    public void setTeacherStateId(Integer teacherStateId) {
        this.teacherStateId = teacherStateId;
    }

    public String getTeacherStateName() {
        return this.teacherStateName;
    }
    
    public void setTeacherStateName(String teacherStateName) {
        this.teacherStateName = teacherStateName;
    }
}