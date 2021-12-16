package com.pintoeat.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pintoeat.api.model.Pin;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PinPojo extends Pin {
	static final long serialVersionUID = 1L;
	public PinPojo() {
		
	}
}
