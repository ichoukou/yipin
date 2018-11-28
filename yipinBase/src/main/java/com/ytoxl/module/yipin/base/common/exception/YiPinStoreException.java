package com.ytoxl.module.yipin.base.common.exception;

import com.ytoxl.module.core.common.exception.YTOXLException;


public class YiPinStoreException extends YTOXLException {
	private final static long serialVersionUID = 6476473452739657628L;
	
	public YiPinStoreException() {
		super();
	}

	public YiPinStoreException(String messageOrCode) {
		super(messageOrCode);
	}

	public YiPinStoreException(String messageOrCode, String[] errorValues) {
		super(messageOrCode,errorValues);		
	}

	public YiPinStoreException(String messageOrCode, String errorValue) {
		super(messageOrCode,errorValue);		
	}
	
	public YiPinStoreException(String messageOrCode, Throwable cause) {
		super(messageOrCode, cause);
	}

	public YiPinStoreException(Throwable cause) {
		super(cause);
	}

	public YiPinStoreException(String messageOrCode, String[] errorValues,
			Throwable cause) {
		super(messageOrCode, errorValues,cause);		
	}
	
	public YiPinStoreException(String messageOrCode, String errorValue,
			Throwable cause) {
		super(messageOrCode, errorValue,cause);		
	}	
}
