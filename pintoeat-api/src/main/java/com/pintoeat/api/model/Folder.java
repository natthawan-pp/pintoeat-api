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
import com.pintoeat.api.pojo.FolderPojo;

@Entity
@Table(name = "folder")
@NamedQuery(name = "Folder.findAll", query = "SELECT e FROM Folder e")
public class Folder implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "name") 
	private String name;
	
	@Column(name = "is_favorite") 
	private boolean isFavorite;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@Column(name = "user_id")
	private String userId;
	
//	// bi-directional many-to-one association to User
//	@ManyToOne
//	@JoinColumn(name="user_id")
//	@JsonBackReference
//	private User userId;
	
	@OneToMany(mappedBy = "folderId", cascade = CascadeType.MERGE)
//	@JsonManagedReference
	private List<Pin> pin;

	public Folder() {
		
	}
	
	public Folder(FolderPojo folderPojo) {
		this.id = folderPojo.getId();
		this.name = folderPojo.getName();
		this.isFavorite = folderPojo.isFavorite();
		this.createdAt = folderPojo.getCreatedAt();
		this.updatedAt = folderPojo.getUpdatedAt();
		this.pin = folderPojo.getPin();
		this.userId = folderPojo.getUserId();
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

	public List<Pin> getPin() {
		return pin;
	}

	public void setPin(List<Pin> pin) {
		this.pin = pin;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
