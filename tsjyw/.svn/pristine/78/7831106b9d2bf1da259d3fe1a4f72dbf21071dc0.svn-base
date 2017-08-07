package edu.iasd.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.iasd.pojo.GoodsType;

public class GoodsTypeVo implements Serializable{
	private static final long serialVersionUID = 5551159299968990428L;
	private Integer goodsTypeId;
	private String goodsTypeName;
	private String goodsTypeNumber;
	private Integer parentGoodsTypeId;  
	private Integer goodsTypeSerial;
	
	private Set goods = new HashSet(0);
	
    public GoodsTypeVo(){
		
	}
	
	public GoodsTypeVo(GoodsType o){
		this.goodsTypeId=o.getGoodsTypeId();
		this.goodsTypeName=o.getGoodsTypeName();
		this.goodsTypeNumber=o.getGoodsTypeNumber();
		this.goodsTypeSerial=o.getGoodsTypeSerial();
		this.parentGoodsTypeId=o.getParentGoodsTypeId();
	}
	public Integer getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(Integer goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	public String getGoodsTypeNumber() {
		return goodsTypeNumber;
	}
	public void setGoodsTypeNumber(String goodsTypeNumber) {
		this.goodsTypeNumber = goodsTypeNumber;
	}
	public Integer getParentGoodsTypeId() {
		return parentGoodsTypeId;
	}
	public void setParentGoodsTypeId(Integer parentGoodsTypeId) {
		this.parentGoodsTypeId = parentGoodsTypeId;
	}
	public Integer getGoodsTypeSerial() {
		return goodsTypeSerial;
	}
	public void setGoodsTypeSerial(Integer goodsTypeSerial) {
		this.goodsTypeSerial = goodsTypeSerial;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Set getGoods() {
		return goods;
	}

	public void setGoods(Set goods) {
		this.goods = goods;
	}

	public static List<GoodsTypeVo> convert(Set al) {
		// TODO Auto-generated method stub
		List<GoodsTypeVo> list = new ArrayList<GoodsTypeVo>();
		for (Object o : al) {
			list.add(new GoodsTypeVo((GoodsType)o));
		}
		return list;
	}
}
