package edu.iasd.form;

import java.io.Serializable;
import java.util.List;

import edu.iasd.pojo.Attachment;

public class AdvertisementForm implements Serializable{
	private Integer advertisementId;
	private Integer attachmentId;
	private String advertisementName;
	private String advertisementImgPath;
	
	private List<Attachment> attachment;
	private  List<AttachVO> pictures;
	
	
	public String getAdvertisementImgPath() {
		return advertisementImgPath;
	}
	public void setAdvertisementImgPath(String advertisementImgPath) {
		this.advertisementImgPath = advertisementImgPath;
	}
	public List<AttachVO> getPictures() {
		return pictures;
	}
	public void setPictures(List<AttachVO> pictures) {
		this.pictures = pictures;
	}
	public Integer getAdvertisementId() {
		return advertisementId;
	}
	public void setAdvertisementId(Integer advertisementId) {
		this.advertisementId = advertisementId;
	}
	public Integer getAttachmentId() {
		return attachmentId;
	}
	public void setAttachmentId(Integer attachmentId) {
		this.attachmentId = attachmentId;
	}
	public String getAdvertisementName() {
		return advertisementName;
	}
	public void setAdvertisementName(String advertisementName) {
		this.advertisementName = advertisementName;
	}
	public List<Attachment> getAttachment() {
		return attachment;
	}
	public void setAttachment(List<Attachment> attachment) {
		this.attachment = attachment;
	}
	
	
	

}
