package com.bank.exceptions;

public class BankCustomerException extends RuntimeException
{
	private String exceptionMessage;
	
	public BankCustomerException() {}
	public BankCustomerException(String exceptionMessage)
	{
		this.exceptionMessage=exceptionMessage;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
}
