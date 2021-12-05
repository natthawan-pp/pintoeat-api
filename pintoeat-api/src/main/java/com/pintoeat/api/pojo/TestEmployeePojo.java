package com.pintoeat.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pintoeat.api.model.TestEmployee;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TestEmployeePojo extends TestEmployee {
	static final long serialVersionUID = 1L;
	public TestEmployeePojo() {
		
	}
}
