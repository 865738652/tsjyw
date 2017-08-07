package edu.iasd.form;

import java.io.Serializable;

public class AttachVO implements Serializable {
	private Integer attachId;
	private String attachName;
	private String attachUrl;
    private String attachIntro;
    private Integer attachSerial;
    private Float attachSize;
    private Integer attachTypeId;
    private String attachTypeName;
    private Integer ownerId;
    private Boolean isThumbnail;
    private Boolean isShow;
    
    public AttachVO() {
    	isThumbnail = false;
    	isShow = true;
    }
	public Integer getAttachId() {
		return attachId;
	}
	public void setAttachId(Integer attachId) {
		this.attachId = attachId;
	}
	public String getAttachName() {
		return attachName;
	}
	public void setAttachName(String attachName) {
		this.attachName = attachName;
	}
	public String getAttachUrl() {
		return attachUrl;
	}
	public void setAttachUrl(String attachUrl) {
		this.attachUrl = attachUrl;
	}
	public String getAttachIntro() {
		return attachIntro;
	}
	public void setAttachIntro(String attachIntro) {
		this.attachIntro = attachIntro;
	}
	public Integer getAttachSerial() {
		return attachSerial;
	}
	public void setAttachSerial(Integer attachSerial) {
		this.attachSerial = attachSerial;
	}
	public Float getAttachSize() {
		return attachSize;
	}
	public void setAttachSize(Float attachSize) {
		this.attachSize = attachSize;
	}
	public Integer getAttachTypeId() {
		return attachTypeId;
	}
	public void setAttachTypeId(Integer attachTypeId) {
		this.attachTypeId = attachTypeId;
	}
	public String getAttachTypeName() {
		return attachTypeName;
	}
	public void setAttachTypeName(String attachTypeName) {
		this.attachTypeName = attachTypeName;
	}
	public Integer getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	public Boolean getIsThumbnail() {
		return isThumbnail;
	}

	public void setIsThumbnail(Boolean isThumbnail) {
		this.isThumbnail = isThumbnail;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
}
