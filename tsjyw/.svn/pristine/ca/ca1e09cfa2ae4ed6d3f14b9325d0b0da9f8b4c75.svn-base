package edu.iasd.pojo;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import edu.iasd.form.AgeLevelVO;
import edu.iasd.form.AskQuestionTypeVO;

@Entity
@Table(name="v_volunteer")
public class ViewVolunteer extends ViewUserBase{
	private static final long serialVersionUID = -7674269980281525366L;
	
	@Column
	private String volunteerIntro;
	
	@Column
	private String volunteerLocation;
	
	@Column
	private Double volunteerAddrLng;
	
	@Column
    private Double volunteerAddrLat;
	
	@Column
	private String volunteerProvince;
	
	@Column
    private String volunteerCity;
	
	@Column
    private String volunteerCounty;
	
	@Column
    private String volunteerPCC;

	@Column
	private String askQuestionTypeNames;
	
	@Transient
	private List<AgeLevelVO> ageLevels;
	
	@Transient
	private List<AskQuestionTypeVO> askQuestionTypes;
	
	public String getVolunteerIntro(){
		return volunteerIntro;
	}
	
	public String getVolunteerLocation() {
		return volunteerLocation;
	}

	public void setVolunteerLocation(String volunteerLocation) {
		this.volunteerLocation = volunteerLocation;
	}

	public String getVolunteerProvince() {
		return volunteerProvince;
	}

	public void setVolunteerProvince(String volunteerProvince) {
		this.volunteerProvince = volunteerProvince;
	}

	public String getVolunteerCity() {
		return volunteerCity;
	}

	public void setVolunteerCity(String volunteerCity) {
		this.volunteerCity = volunteerCity;
	}

	public String getVolunteerCounty() {
		return volunteerCounty;
	}

	public void setVolunteerCounty(String volunteerCounty) {
		this.volunteerCounty = volunteerCounty;
	}

	public String getVolunteerPCC() {
		return volunteerPCC;
	}

	public void setVolunteerPCC(String volunteerPCC) {
		this.volunteerPCC = volunteerPCC;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setVolunteerIntro(String volunteerIntro) {
		this.volunteerIntro = volunteerIntro;
	}

	public Double getVolunteerAddrLng() {
		return volunteerAddrLng;
	}

	public void setVolunteerAddrLng(Double volunteerAddrLng) {
		this.volunteerAddrLng = volunteerAddrLng;
	}

	public Double getVolunteerAddrLat() {
		return volunteerAddrLat;
	}

	public void setVolunteerAddrLat(Double volunteerAddrLat) {
		this.volunteerAddrLat = volunteerAddrLat;
	}
	
	public String getAskQuestionTypeNames() {
		return askQuestionTypeNames;
	}

	public void setAskQuestionTypeNames(String askQuestionTypeNames) {
		this.askQuestionTypeNames = askQuestionTypeNames;
	}
}
