package com.pintoeat.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pintoeat.api.model.Folder;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FolderPojo extends Folder {
	static final long serialVersionUID = 1L;
	public FolderPojo() {
		
	}
}
