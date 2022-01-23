package com.pintoeat.api.pojo;

import java.util.Date;
import java.util.List;

import com.pintoeat.api.model.Pin;
import com.pintoeat.api.model.User;

public class FolderOutput {
	
	private String id;
	private String name;
	private boolean isFavorite;
	private List<Pin> pin;
	private Integer pinCount; 
	
	public FolderOutput () {
		
	}

	public FolderOutput(String id, String name, boolean isFavorite, List<Pin> pin) {
		this.id = id;
		this.name = name;
		this.isFavorite = isFavorite;
		this.pin = pin;
	}

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

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public List<Pin> getPin() {
		return pin;
	}

	public void setPin(List<Pin> pin) {
		this.pin = pin;
	}

	public Integer getPinCount() {
		return pinCount;
	}

	public void setPinCount(Integer pinCount) {
		this.pinCount = pinCount;
	} 
	
	
	

}
