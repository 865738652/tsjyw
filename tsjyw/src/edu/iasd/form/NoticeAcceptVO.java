package edu.iasd.form;

import java.io.Serializable;

public class NoticeAcceptVO implements Serializable {
	private static final long serialVersionUID = 4575887838959241492L;
	private Integer noticeAcceptId;
    private Integer noticeAcceptTypeId;
    private Integer noticeAcceptGroupId;
	public Integer getNoticeAcceptId() {
		return noticeAcceptId;
	}
	public void setNoticeAcceptId(Integer noticeAcceptId) {
		this.noticeAcceptId = noticeAcceptId;
	}
	public Integer getNoticeAcceptTypeId() {
		return noticeAcceptTypeId;
	}
	public void setNoticeAcceptTypeId(Integer noticeAcceptTypeId) {
		this.noticeAcceptTypeId = noticeAcceptTypeId;
	}
	public Integer getNoticeAcceptGroupId() {
		return noticeAcceptGroupId;
	}
	public void setNoticeAcceptGroupId(Integer noticeAcceptGroupId) {
		this.noticeAcceptGroupId = noticeAcceptGroupId;
	}
}
