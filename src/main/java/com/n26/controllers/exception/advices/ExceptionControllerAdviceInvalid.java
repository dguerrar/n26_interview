package com.n26.controllers.exception.advices;
/**
 * control the selected Exceptions and return the desired response http code 400
 * 
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.generic.GenericModule;

@ControllerAdvice
public class ExceptionControllerAdviceInvalid extends GenericModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8095484191356370603L;

	@ExceptionHandler(InvalidTransactionException.class)
	public ResponseEntity exceptionHandler(InvalidTransactionException ex) {
		log.error("fOUND ExceptionControllerAdviceInvalid");
		 return new ResponseEntity("", HttpStatus.BAD_REQUEST);//400
	}

	@Override
	protected Class<?> getLogClass() {
		// TODO Auto-generated method stub
		return ExceptionControllerAdviceInvalid.class;
	}
}
