package com.pintoeat.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pintoeat.api.model.Image;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ImagePojo extends Image {
	static final long serialVersionUID = 1L;
	public ImagePojo() {
		
	}
}
