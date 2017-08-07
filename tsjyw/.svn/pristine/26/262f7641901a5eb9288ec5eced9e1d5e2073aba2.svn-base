package edu.iasd.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;

@Entity
@Table(name = "v_famousteacher")
public class ViewFamousTeacher extends ViewUserBase {
	private static final long serialVersionUID = -7674269980281525366L;
	
	@Column
	private String famousTeacherIntro;
	
	@Column
	private Boolean recommend;

	@Column
	private String askQuestionTypeNames;
	
	@Transient
	private List<AgeLevelVO> ageLevels;
	
	@Transient
	private List<AskQuestionTypeVO> askQuestionTypes;

	public String getFamousTeacherIntro() {
		return famousTeacherIntro;
	}

	public void setFamousTeacherIntro(String famousTeacherIntro) {
		this.famousTeacherIntro = famousTeacherIntro;
	}

	public Boolean getRecommend() {
		return recommend;
	}

	public void setRecommend(Boolean recommend) {
		this.recommend = recommend;
	}

	public List<AgeLevelVO> getAgeLevels() {
		return ageLevels;
	}

	public void setAgeLevels(List<AgeLevelVO> ageLevels) {
		this.ageLevels = ageLevels;
	}

	public List<AskQuestionTypeVO> getAskQuestionTypes() {
		return askQuestionTypes;
	}

	public void setAskQuestionTypes(List<AskQuestionTypeVO> askQuestionTypes) {
		this.askQuestionTypes = askQuestionTypes;
	}
	
	public String getAskQuestionTypeNames() {
		return askQuestionTypeNames;
	}

	public void setAskQuestionTypeNames(String askQuestionTypeNames) {
		this.askQuestionTypeNames = askQuestionTypeNames;
	}
}
