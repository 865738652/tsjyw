package edu.iasd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonResult {
	private String code;
	private int total;
	private Object rows;
	private Object data;
	
	public JsonResult(String code, int total, Object rows, Object data) {
		this.code = code;
		this.total = total;
		this.rows = rows;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public Object getRows() {
		return rows;
	}
	public void setRows(Object rows) {
		this.rows = rows;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public String toJsonString() throws Exception {
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(this);
		System.out.println(jsonString);
		return jsonString;
	}
}
