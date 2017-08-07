package edu.iasd.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.iasd.pojo.AskQuestionType;

public class AskQuestionTypeVO implements Serializable {
	private static final long serialVersionUID = 7982456886819565364L;
	private Integer askQuestionTypeId;
	private String askQuestionTypeName;
	private Integer aspQuestionTypePublic;
	private Integer askQuestionTypeFlag;
	
	public AskQuestionTypeVO() {
		
	}
	
	public AskQuestionTypeVO(AskQuestionType t) {
		this.askQuestionTypeId = t.getAskQuestionTypeId();
		this.askQuestionTypeName = t.getAskQuestionTypeName();
		this.aspQuestionTypePublic = t.getAspQuestionTypePublic();
		this.askQuestionTypeFlag = t.getAskQuestionTypeFlag();
	}
	public Integer getAskQuestionTypeId() {
		return askQuestionTypeId;
	}
	public void setAskQuestionTypeId(Integer askQuestionTypeId) {
		this.askQuestionTypeId = askQuestionTypeId;
	}
	public String getAskQuestionTypeName() {
		return askQuestionTypeName;
	}
	public void setAskQuestionTypeName(String askQuestionTypeName) {
		this.askQuestionTypeName = askQuestionTypeName;
	}
	public Integer getAspQuestionTypePublic() {
		return aspQuestionTypePublic;
	}
	public void setAspQuestionTypePublic(Integer aspQuestionTypePublic) {
		this.aspQuestionTypePublic = aspQuestionTypePublic;
	}	
	public Integer getAskQuestionTypeFlag() {
		return askQuestionTypeFlag;
	}

	public void setAskQuestionTypeFlag(Integer askQuestionTypeFlag) {
		this.askQuestionTypeFlag = askQuestionTypeFlag;
	}

	public static List<AskQuestionTypeVO> convert(Set at) {
		List<AskQuestionTypeVO> list = new ArrayList<AskQuestionTypeVO>();
		for (Object o : at) {
			list.add(new AskQuestionTypeVO((AskQuestionType)o));
		}
		return list;
	}
	
	public static List<AskQuestionTypeVO> convert(List<AskQuestionType> at) {
		List<AskQuestionTypeVO> list = new ArrayList<AskQuestionTypeVO>();
		for (AskQuestionType o : at) {
			list.add(new AskQuestionTypeVO(o));
		}
		return list;
	}
}
