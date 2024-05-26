package com.invoice.model;
import lombok.Builder;

@Builder
public class ErrorResponseModel
{
	private String errorMsg;
	private int errorCode;
	public ErrorResponseModel()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	public ErrorResponseModel(String errorMsg, int errorCode)
	{
		super();
		this.errorMsg = errorMsg;
		this.errorCode = errorCode;
	}
	public String getErrorMsg()
	{
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg)
	{
		this.errorMsg = errorMsg;
	}
	public int getErrorCode()
	{
		return errorCode;
	}
	public void setErrorCode(int errorCode)
	{
		this.errorCode = errorCode;
	}
	
}
