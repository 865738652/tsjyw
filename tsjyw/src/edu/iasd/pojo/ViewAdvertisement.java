package edu.iasd.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "v_advertisement")
public class ViewAdvertisement implements Serializable{
	private Integer advertisementId;
	private String advertisementName;
	private Integer attachmentId;
	private String attachmentName;
	private String attachmentUrl;
	private String attachmentTypeId;
	private String attachmentSize;
	private String advertisementImgPath;
	
	private Set attachment = new HashSet(0);
	
	
	
	public String getAdvertisementImgPath() {
		return advertisementImgPath;
	}
	public void setAdvertisementImgPath(String advertisementImgPath) {
		this.advertisementImgPath = advertisementImgPath;
	}
	public String getAttachmentName() {
		return attachmentName;
	}
	public void setAttachmentName(String attachmentName) {
		this.attachmentName = attachmentName;
	}
	public String getAttachmentUrl() {
		return attachmentUrl;
	}
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}
	public String getAttachmentTypeId() {
		return attachmentTypeId;
	}
	public void setAttachmentTypeId(String attachmentTypeId) {
		this.attachmentTypeId = attachmentTypeId;
	}
	public String getAttachmentSize() {
		return attachmentSize;
	}
	public void setAttachmentSize(String attachmentSize) {
		this.attachmentSize = attachmentSize;
	}
	public Integer getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public Set getAttachment() {
		return attachment;
	}
	public void setAttachment(Set attachment) {
		this.attachment = attachment;
	}
	
	
	
	
}
