package com.bank.DAO;

import java.util.List;

import com.bank.model.BankCustomerDetails;

public interface BankCustomerDAO 
{
	//abstract method Which is used to insert bankCustomerDetails(Object) data into the data base for every customer
	void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails);
	//To store the data of the Customer Details In List collection 
	List<BankCustomerDetails> getAllCustomerDetails();
	
	void updateAccountNumberAndPinByUsingId(BankCustomerDetails bankCustomerDetails);
	
	void deleteCustomerDetailsByUsingId(BankCustomerDetails bankCustomerDetails);
	
	BankCustomerDetails selectCustomerDetailsByUsingEmailidAndPin(String emailid, int pin);
	
	boolean updateBalanaceAmountByUsingAccountNumber(double amount, int accountNumber);
}