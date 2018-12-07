package com.n26.controllers.exception.advices;
/**
 * control the selected Exceptions and return the desired response http code 400
 * 
 */
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.n26.controllers.exception.TransactionBindingException;
import com.n26.generic.GenericModule;


@ControllerAdvice
public class ExceptionControllerAdviceBinding extends GenericModule {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1772395021354429794L;

	@ExceptionHandler(	IllegalArgumentException.class)
	public ResponseEntity exceptionHandler(TransactionBindingException ex) {
		log.error("fOUND ExceptionControllerAdviceBinding");
		return new ResponseEntity("", HttpStatus.BAD_REQUEST);//400
		
	}

	@Override
	protected Class<?> getLogClass() {
		// TODO Auto-generated method stub
		return ExceptionControllerAdviceBinding.class;
	}
	
	 
}
