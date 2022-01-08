package com.pintoeat.api.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Blob;
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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pintoeat.api.pojo.PinPojo;

@Entity
@Table(name = "pin")
@NamedQuery(name = "Pin.findAll", query = "SELECT e FROM Pin e")
public class Pin implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name") 
	private String name;
	
	@Column(name = "description") 
	private String description;
	
	@Column(name = "location") 
	private String location;
	
	@Column(name = "is_favorite") 
	private boolean isFavorite;
	
	@Column(name = "is_bookmark") 
	private boolean isBookmark;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "folder_id")
	private String folderId;
	
//	//bi-directional many-to-one association to Folder
//	@ManyToOne
//	@JoinColumn(name="folder_id")
//	@JsonBackReference
//	private Folder folderId;
	
	@OneToMany(mappedBy = "pinId", cascade = CascadeType.MERGE)
//	@JsonManagedReference
	private List<Image> image;

	public Pin() {
		
	}


	public Pin(PinPojo pinPojo) {
		this.id = pinPojo.getId();
		this.name = pinPojo.getName();
		this.description = pinPojo.getDescription();
		this.location = pinPojo.getLocation();
		this.isFavorite = pinPojo.isFavorite();
		this.isBookmark = pinPojo.isBookmark();
		this.createdAt = pinPojo.getCreatedAt();
		this.updatedAt = pinPojo.getUpdatedAt();
		this.image = pinPojo.getImage();
		this.folderId = pinPojo.getFolderId();
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


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isFavorite() {
		return isFavorite;
	}

	public void setFavorite(boolean isFavorite) {
		this.isFavorite = isFavorite;
	}

	public boolean isBookmark() {
		return isBookmark;
	}

	public void setBookmark(boolean isBookmark) {
		this.isBookmark = isBookmark;
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
	
	public String getFolderId() {
		return folderId;
	}

	public void setFolderId(String folderId) {
		this.folderId = folderId;
	}

	public List<Image> getImage() {
		return image;
	}

	public void setImage(List<Image> image) {
		this.image = image;
	}
	
	
	
	

}
