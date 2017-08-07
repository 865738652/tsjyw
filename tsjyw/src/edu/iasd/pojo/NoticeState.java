package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * NoticeState entity. @author MyEclipse Persistence Tools
 */

public class NoticeState  implements java.io.Serializable {


    // Fields    
     public static final Integer NOTICESTATE_NORMAL = 1;
     public static final Integer NOTICESTATE_DRAFT = 2;
     public static final Integer NOTICESTATE_CANCEL = 3;
     
     
     private Integer noticeStateId;
     private String noticeStateName;
     private Set notices = new HashSet(0);


    // Constructors

    /** default constructor */
    public NoticeState() {
    }

	/** minimal constructor */
    public NoticeState(String noticeStateName) {
        this.noticeStateName = noticeStateName;
    }
    
    /** full constructor */
    public NoticeState(String noticeStateName, Set notices) {
        this.noticeStateName = noticeStateName;
        this.notices = notices;
    }

   
    // Property accessors

    public Integer getNoticeStateId() {
        return this.noticeStateId;
    }
    
    public void setNoticeStateId(Integer noticeStateId) {
        this.noticeStateId = noticeStateId;
    }

    public String getNoticeStateName() {
        return this.noticeStateName;
    }
    
    public void setNoticeStateName(String noticeStateName) {
        this.noticeStateName = noticeStateName;
    }

    public Set getNotices() {
        return this.notices;
    }
    
    public void setNotices(Set notices) {
        this.notices = notices;
    }
   








}