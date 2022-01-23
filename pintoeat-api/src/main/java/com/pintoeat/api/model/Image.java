package com.pintoeat.api.model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.sql.Blob;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pintoeat.api.pojo.ImageConvertPojo;
import com.pintoeat.api.pojo.ImagePojo;
import com.pintoeat.api.pojo.UserPojo;

@Entity
@Table(name = "image")
@NamedQuery(name = "Image.findAll", query = "SELECT e FROM Image e")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "image") 
	private String image;
	
	@Column(name = "priority")
	private Integer priority;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "pin_id")
	private String pinId;
	
//	//bi-directional many-to-one association to Pin
//	@ManyToOne
//	@JoinColumn(name="pin_id")
//	@JsonBackReference
//	private Pin pinId;

	public Image() {
		
	}
	
//	public Image(ImagePojo imagePojo) {
//		this.id = imagePojo.getId();
//		this.image = imagePojo.getImage();
//		this.priority = imagePojo.getPriority();
//		this.createdAt = imagePojo.getCreatedAt();
//		this.updatedAt = imagePojo.getUpdatedAt();
//		this.pinId = imagePojo.getPinId();
//	}
	
	public Image(ImageConvertPojo imageConvertPojo) {
		this.id = imageConvertPojo.getId();
		this.image = encodeFileToBase64(imageConvertPojo.getImage());
		this.priority = imageConvertPojo.getPriority();
		this.createdAt = imageConvertPojo.getCreatedAt();
		this.updatedAt = imageConvertPojo.getUpdatedAt();
		this.pinId = imageConvertPojo.getPinId();
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getImage() {
		return image;
	}


	public void setImage(String image) {
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
	
	private static String encodeFileToBase64(MultipartFile multipartFile) {
	    try {
//	        byte[] fileContent = Files.readAllBytes(multipartFile.toPath());
//	        return Base64.getEncoder().encodeToString(fileContent);
	    	File file = convertMultiPartToFile(multipartFile);
	    	byte[] fileContent = Files.readAllBytes(file.toPath());
	    	String addIn = "data:image/jpeg;base64,/";
	    	String base64String = Base64.getEncoder().encodeToString(fileContent);
	    	String newString = base64String.substring(0,0) + addIn + base64String.substring(1);
	    	return newString;
	    } catch (IOException e) {
	        throw new IllegalStateException("could not read file " + multipartFile, e);
	    }
	}
	
	 private static File convertMultiPartToFile(MultipartFile file) throws IOException{
	       File convFile = new File( file.getOriginalFilename() );
	       FileOutputStream fos = new FileOutputStream( convFile );
	       fos.write( file.getBytes() );
	       fos.close();
	       return convFile;
	 }
	

}
