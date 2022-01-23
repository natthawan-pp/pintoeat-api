package com.pintoeat.api.pojo;

public class LoginOutput {

	private String responseMsg;
	private String userId;
	private String defaultFolderId;

	public LoginOutput() {

	}

	public LoginOutput(String responseMsg, String userId, String defaultFolderId) {
		this.responseMsg = responseMsg;
		this.userId = userId;
		this.defaultFolderId = defaultFolderId;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDefaultFolderId() {
		return defaultFolderId;
	}

	public void setDefaultFolderId(String defaultFolderId) {
		this.defaultFolderId = defaultFolderId;
	}

	

}
