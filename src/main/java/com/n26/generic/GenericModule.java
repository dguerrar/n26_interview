package com.n26.generic;
/**
 * base class in order to put generic stuff for modules
 * 
 */
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class GenericModule implements Serializable {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 9198492554992798385L;
	/**
	 * 
	 * this way, only have to define the logger here and extends in other classes
	 */
	protected Logger log = LoggerFactory.getLogger(getLogClass());
	protected abstract Class<?> getLogClass();
	
}
