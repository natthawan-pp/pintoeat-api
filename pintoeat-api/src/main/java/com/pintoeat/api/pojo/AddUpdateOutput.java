package com.pintoeat.api.pojo;

public class AddUpdateOutput {

	private String responseMsg;
	private String rowId;

	public AddUpdateOutput() {

	}

	public AddUpdateOutput(String responseMsg, String rowId) {
		this.responseMsg = responseMsg;
		this.rowId = rowId;
	}

	public String getResponseMsg() {
		return responseMsg;
	}

	public void setResponseMsg(String responseMsg) {
		this.responseMsg = responseMsg;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

}
