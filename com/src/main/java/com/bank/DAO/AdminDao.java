package com.bank.DAO;

public interface AdminDao 
{
	//abstract method to fetch details from the admin and login in to the account
	boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid, String password);
}
