package com.n26.controllers.exception.advices;
/**
 * control the selected Exceptions and return the desired response http code 204
 * 
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.n26.controllers.exception.InvalidTransactionException;
import com.n26.controllers.exception.OlderTransactionException;
import com.n26.controllers.exception.ParseableTransactionRuntimeException;
import com.n26.controllers.exception.TransactionRuntimeException;
import com.n26.generic.GenericModule;

@ControllerAdvice
public class ExceptionControllerAdviceOlder extends GenericModule {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8252420275018717896L;

	@ExceptionHandler(OlderTransactionException.class)
	public ResponseEntity exceptionHandler(OlderTransactionException ex) {
		log.error("fOUND ExceptionControllerAdviceOlder");
		 return new ResponseEntity("", HttpStatus.NO_CONTENT);//204
	}

	@Override
	protected Class<?> getLogClass() {
		// TODO Auto-generated method stub
		return ExceptionControllerAdviceOlder.class;
	}
}
