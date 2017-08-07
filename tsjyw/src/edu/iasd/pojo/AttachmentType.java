package edu.iasd.pojo;
// default package

import java.util.HashSet;
import java.util.Set;


/**
 * AttachmentType entity. @author MyEclipse Persistence Tools
 */

public class AttachmentType  implements java.io.Serializable {


	public static final Integer ATTACHMENT_IMAGE = 1;
	public static final String[] IMAGES = new String[]{"BMP","GIF","JPEG","PNG","PSD","JPG"};
	
	public static final Integer ATTACHMENT_TXT = 2;
	public static final String[] TXTS = new String[]{"TXT"};
			
	public static final Integer ATTACHMENT_WORD = 3;
	public static final String[] WORDS = new String[]{"DOC","DOCX"};
	
	public static final Integer ATTACHMENT_EXCEL = 4;
	public static final String[] EXCELS = new String[]{"XLS"};
	
	public static final Integer ATTACHMENT_VIDEO = 5;
	public static final String[] VIDEOS = new String[]{"MP4","MPG","AVI","RM","RMVB","WMV"};
	
	public static final Integer ATTACHMENT_ZIP = 6;
	public static final String[] ZIPS = new String[]{"RAR","ZIP"};
	
	public static final Integer ATTACHMENT_OTHER = 7;
    // Fields    

     private Integer attachmentTypeId;
     private String attachmentTypeName;

     
    // Constructors

    /** default constructor */
    public AttachmentType() {
    }

	/** minimal constructor */
    public AttachmentType(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }
   
    // Property accessors

    public Integer getAttachmentTypeId() {
        return this.attachmentTypeId;
    }
    
    public void setAttachmentTypeId(Integer attachmentTypeId) {
        this.attachmentTypeId = attachmentTypeId;
    }

    public String getAttachmentTypeName() {
        return this.attachmentTypeName;
    }
    
    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }
}