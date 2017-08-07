package edu.iasd.pojo;
// default package

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


/**
 * Announcement entity. @author MyEclipse Persistence Tools
 */

public class Announcement  implements java.io.Serializable {


    // Fields    

     private Integer announcementId;
     private String announcementTitle;
     private String announcementContent;
     private Timestamp announcementTime;


    // Constructors

    /** default constructor */
    public Announcement() {
    }

	/** minimal constructor */
    public Announcement(String announcementTitle, String announcementContent, Timestamp announcementTime) {
        this.announcementTitle = announcementTitle;
        this.announcementContent = announcementContent;
        this.announcementTime = announcementTime;
    }

	public Integer getAnnouncementId() {
		return announcementId;
	}

	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}

	public String getAnnouncementTitle() {
		return announcementTitle;
	}

	public void setAnnouncementTitle(String announcementTitle) {
		this.announcementTitle = announcementTitle;
	}

	public String getAnnouncementContent() {
		return announcementContent;
	}

	public void setAnnouncementContent(String announcementContent) {
		this.announcementContent = announcementContent;
	}

	public Timestamp getAnnouncementTime() {
		return announcementTime;
	}

	public void setAnnouncementTime(Timestamp announcementTime) {
		this.announcementTime = announcementTime;
	}
    
    /** full constructor */
    

   
    // Property accessors
}