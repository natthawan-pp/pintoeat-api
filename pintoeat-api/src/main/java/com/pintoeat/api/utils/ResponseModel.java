package com.pintoeat.api.utils;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import org.springframework.http.HttpStatus;

public class ResponseModel<T> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String  massgeCode;
	private Timestamp  massgeTime = new Timestamp(new Date().getTime());
	private T massgeData;
	
	
	public String getMassgeCode() {
		return massgeCode;
	}
	public void setMassgeCode(HttpStatus massgeCode) {
		this.massgeCode = String.valueOf(massgeCode.toString());
	}
	public Timestamp getMassgeTime() {
		return massgeTime;
	}
	public T getMassgeData() {
		return massgeData;
	}
	public void setMassgeData(T massgeData) {
		this.massgeData = massgeData;
	}
	
}
