package com.pintoeat.api.pojo;

import java.util.List;

import com.pintoeat.api.model.Image;

public class NearByOutput {
	
	private String id;
	private double length;
	private String name;
	private boolean isFavorite;
	private List<Image>image;
	
	public NearByOutput() {
		
	}
	
	public NearByOutput(String id, double length, String name, boolean isFavorite, List<Image> image) {
		this.id = id;
		this.length = length;
		this.name = name;
		this.isFavorite = isFavorite;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
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

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}

}
