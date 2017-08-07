package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * NoticeAcceptType entity. @author MyEclipse Persistence Tools
 */

public class NoticeAcceptType  implements java.io.Serializable {


    // Fields    
	
	 public final static int NOTICEACCEPTTYPE_SCHOOL      = 1;
	 public final static int NOTICEACCEPTTYPE_GRADE       = 2;
	 public final static int NOTICEACCEPTTYPE_SCHOOLCLASS = 3;
	 public final static int NOTICEACCEPTTYPE_PERSON      = 4;
	 
	 public final static int NOITCEACCEPTTYPE_ALLSCHOOL   = 5;
	 public final static int NOITCEACCEPTTYPE_ALLUSER     = 6;
	

     private Integer noticeAcceptTypeId;
     private String noticeAcceptTypeName;
     private Set noticeAccepts = new HashSet(0);
     private Set notices = new HashSet(0);


    // Constructors

    /** default constructor */
    public NoticeAcceptType() {
    }

	/** minimal constructor */
    public NoticeAcceptType(String noticeAcceptTypeName) {
        this.noticeAcceptTypeName = noticeAcceptTypeName;
    }

	public Integer getNoticeAcceptTypeId() {
		return noticeAcceptTypeId;
	}

	public void setNoticeAcceptTypeId(Integer noticeAcceptTypeId) {
		this.noticeAcceptTypeId = noticeAcceptTypeId;
	}

	public String getNoticeAcceptTypeName() {
		return noticeAcceptTypeName;
	}

	public void setNoticeAcceptTypeName(String noticeAcceptTypeName) {
		this.noticeAcceptTypeName = noticeAcceptTypeName;
	}

	public Set getNoticeAccepts() {
		return noticeAccepts;
	}

	public void setNoticeAccepts(Set noticeAccepts) {
		this.noticeAccepts = noticeAccepts;
	}

	public Set getNotices() {
		return notices;
	}

	public void setNotices(Set notices) {
		this.notices = notices;
	}
    
    /** full constructor */
    
   
    // Property accessors








}