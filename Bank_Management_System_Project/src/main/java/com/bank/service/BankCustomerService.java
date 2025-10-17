package com.bank.service;

public interface BankCustomerService 
{
	//Service layer for monolithic architecture
	//Abstract CustomerDetails Method which should by implemented by The implementation class
	void customerDetails();
	
	void customerLogin();
	
	void bankCustomerOperation();
	
	void credit();
	
	void debit();
	
	void checkStatement();
	
	void balanceCheck();
}
