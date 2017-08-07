package edu.iasd.form;

import java.util.List;

public class FamousTeacherForm extends UserForm {
	private String famousTeacherIntro;
	private Boolean recommend;
	private List<Integer> askQuestionTypes;
    private List<Integer> ageLevels;
    
	public FamousTeacherForm() {
		recommend = false;
	}
	
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

	public List<Integer> getAskQuestionTypes() {
		return askQuestionTypes;
	}

	public void setAskQuestionTypes(List<Integer> askQuestionTypes) {
		this.askQuestionTypes = askQuestionTypes;
	}

	public List<Integer> getAgeLevels() {
		return ageLevels;
	}

	public void setAgeLevels(List<Integer> ageLevels) {
		this.ageLevels = ageLevels;
	}
}
