package com.example.dentalClinic.apiHandler;

public class ApiRequestException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	

	public ApiRequestException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}
	public ApiRequestException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}
	public ApiRequestException() {
	}
	public ApiResult api (Object obj) 
	{
		ApiResult result=new ApiResult();
		result.setStatusCode("0");
		result.setStatusDescription("Success");
		result.setResult(obj);
		return result;
	}
}
