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
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.pintoeat.api.pojo.UserPojo;

@Entity
@Table(name = "user")
@NamedQuery(name = "User.findAll", query = "SELECT e FROM User e")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;

	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "is_confirm_email") 
	private boolean isConFirmEmail;

	@Column(name = "is_login_by_facebook") 
	private boolean isLoginByFaceBook;
	
	@Column(name = "is_login_by_gmail") 
	private boolean isLoginByGmail;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "created_at")
	private Date createdAt;
	
	@Temporal(TemporalType.DATE) 
	@Column(name = "updated_at")
	private Date updatedAt;
	
	@OneToMany(mappedBy = "userId", cascade = CascadeType.MERGE)
//	@JsonManagedReference
	private List<Folder> folder;

	public User() {
		
	}
	
	public User(UserPojo userPojo) {
		this.id = userPojo.getId();
		this.email = userPojo.getEmail();
		this.password = userPojo.getPassword();
		this.isConFirmEmail = userPojo.isConFirmEmail();
		this.isLoginByFaceBook = userPojo.isLoginByFaceBook();
		this.isLoginByGmail = userPojo.isLoginByGmail();
		this.createdAt = userPojo.getCreatedAt();
		this.updatedAt = userPojo.getUpdatedAt();
		this.folder = userPojo.getFolder();
	}

	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public boolean isConFirmEmail() {
		return isConFirmEmail;
	}


	public void setConFirmEmail(boolean isConFirmEmail) {
		this.isConFirmEmail = isConFirmEmail;
	}


	public boolean isLoginByFaceBook() {
		return isLoginByFaceBook;
	}


	public void setLoginByFaceBook(boolean isLoginByFaceBook) {
		this.isLoginByFaceBook = isLoginByFaceBook;
	}


	public boolean isLoginByGmail() {
		return isLoginByGmail;
	}


	public void setLoginByGmail(boolean isLoginByGmail) {
		this.isLoginByGmail = isLoginByGmail;
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

	public List<Folder> getFolder() {
		return folder;
	}

	public void setFolder(List<Folder> folder) {
		this.folder = folder;
	}
	
}
