package com.invoice.exception;

public class CustomException extends RuntimeException
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int errorCode;

	public CustomException()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	public CustomException(String message)
	{
		super(message);
		// TODO Auto-generated constructor stub
	}
	
	public CustomException(String message,int errorCode)
	{
		super(message);
		this.errorCode = errorCode;
		
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
