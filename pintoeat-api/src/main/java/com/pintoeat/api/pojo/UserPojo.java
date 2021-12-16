package com.pintoeat.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pintoeat.api.model.User;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserPojo extends User {
	static final long serialVersionUID = 1L;
	public UserPojo() {
		
	}
}
