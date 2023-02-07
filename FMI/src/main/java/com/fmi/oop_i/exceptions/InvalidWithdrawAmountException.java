package com.fmi.oop_i.exceptions;

public class InvalidWithdrawAmountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -951101564699036181L;

	public InvalidWithdrawAmountException(String message) {
		super(message);
	}

}
