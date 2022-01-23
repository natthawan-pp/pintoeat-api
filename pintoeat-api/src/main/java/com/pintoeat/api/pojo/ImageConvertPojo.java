package com.pintoeat.api.pojo;

import java.io.File;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ImageConvertPojo {

	private String id;
	private MultipartFile image;
	private Integer priority;
	private Date createdAt;
	private Date updatedAt;
	private String pinId;
	
	ImageConvertPojo(){
		
	}

	public ImageConvertPojo(String id, MultipartFile image, Integer priority, Date createdAt, Date updatedAt, String pinId) {
		super();
		this.id = id;
		this.image = image;
		this.priority = priority;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.pinId = pinId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile  image) {
		this.image = image;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getPinId() {
		return pinId;
	}

	public void setPinId(String pinId) {
		this.pinId = pinId;
	}
	
	

}
