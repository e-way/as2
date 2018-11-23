package com.yy.EmployeeManagement.Service;

public class ResultResponse {
    private String resultCode;
    private String Description;
	public ResultResponse(String resultCode, String description) {
		super();
		this.setResultCode(resultCode);
		Description = description;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
    
}
