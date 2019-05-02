package com.su.hello.bean;

import java.io.Serializable;

import org.springframework.stereotype.Component;

@Component
public class Hello implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYearOld() {
		return yearOld;
	}

	public void setYearOld(Integer yearOld) {
		this.yearOld = yearOld;
	}

	private String name;
	
	private Integer yearOld;

}
