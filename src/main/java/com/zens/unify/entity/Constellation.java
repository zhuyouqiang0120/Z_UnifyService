package com.zens.unify.entity;
/**
 * 星座
 * @author vector
 * @time 2014年7月18日 上午11:48:24
 *
 */
public class Constellation {
	
	private String consName;
	
	private String json;

	public Constellation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Constellation(String consName, String json) {
		super();
		this.consName = consName;
		this.json = json;
	}

	public String getConsName() {
		return consName;
	}

	public void setConsName(String consName) {
		this.consName = consName;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}
	
}
