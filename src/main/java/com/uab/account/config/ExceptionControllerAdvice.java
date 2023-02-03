package com.uab.account.config;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.uab.account.dto.BaseResponse;
import com.uab.account.exception.AccountNotFoundException;
import com.uab.account.exception.InvalidRequestException;

@ControllerAdvice
public class ExceptionControllerAdvice {

	private Logger logger = LoggerFactory.getLogger(ExceptionControllerAdvice.class);

	@ExceptionHandler(value = { AccountNotFoundException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public BaseResponse handeAccountNotFoundException(HttpServletRequest request, AccountNotFoundException ex) {
		logger.error("AccountNotFoundException|RESPONSE|ERROR|" + ex.getMessage(), ex);
		return new BaseResponse(10404, ex.getMessage());
	}

	@ExceptionHandler(value = { InvalidRequestException.class })
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@ResponseBody
	public BaseResponse handeInvalidRequestException(HttpServletRequest request, InvalidRequestException ex) {
		logger.error("InvalidRequestException|RESPONSE|ERROR|" + ex.getMessage(), ex);
		return new BaseResponse(10404, ex.getMessage());
	}

}
