package edu.iasd.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import edu.iasd.pojo.AgeLevel;

public class AgeLevelVO implements Serializable {
	private static final long serialVersionUID = 5551159299968990428L;
	private Integer ageLevelId;
    private String ageName;
    
    public AgeLevelVO() {
    }
    
    public AgeLevelVO(AgeLevel o) {
    	this.ageLevelId = o.getAgeLevelId();
    	this.ageName = o.getAgeName();
    }
	public Integer getAgeLevelId() {
		return ageLevelId;
	}
	public void setAgeLevelId(Integer ageLevelId) {
		this.ageLevelId = ageLevelId;
	}
	public String getAgeName() {
		return ageName;
	}
	public void setAgeName(String ageName) {
		this.ageName = ageName;
	}
	public static List<AgeLevelVO> convert(Set al) {
		List<AgeLevelVO> list = new ArrayList<AgeLevelVO>();
		for (Object o : al) {
			list.add(new AgeLevelVO((AgeLevel)o));
		}
		return list;
	}
	public static List<AgeLevelVO> convert(List<AgeLevel> al) {
		List<AgeLevelVO> list = new ArrayList<AgeLevelVO>();
		for (AgeLevel o : al) {
			list.add(new AgeLevelVO(o));
		}
		return list;
	}
}
