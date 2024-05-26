package com.invoice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.invoice.model.ErrorResponseModel;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(CustomException.class)
	@ResponseBody
	public ResponseEntity<ErrorResponseModel> handleCustomException(CustomException ex)
	{
		return new ResponseEntity<ErrorResponseModel>(
				new ErrorResponseModel().builder()
				.errorMsg(ex.getMessage())
				.errorCode(ex.getErrorCode())
				.build(),
				convertErrorCodeToHttpStatus(ex.getErrorCode()));
	}

	private HttpStatus convertErrorCodeToHttpStatus(int errorCode)
	{
		switch (errorCode)
		{
		case 400:
			return HttpStatus.BAD_REQUEST;
		case 404:
			return HttpStatus.NOT_FOUND;
		case 500:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		// Add more cases as needed
		default:
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
	}
}
