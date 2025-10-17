package com.bank.service;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.BankCustomerDAO;
import com.bank.DAO.BankCustomerDAOImpl;
import com.bank.DAO.TransactionDAO;
import com.bank.DAO.TransactionDAOimpl;
import com.bank.exceptions.BankCustomerException;
import com.bank.model.BankCustomerDetails;
import com.bank.model.TransactionDetails;

public class BankCustomerServiceImpl implements BankCustomerService
{
	//importing scanner class
	Scanner scan = new Scanner(System.in);
	//Unimplemented method present in service layer
	BankCustomerDAO bankCustomerDAO = new BankCustomerDAOImpl();
	private static BankCustomerDetails loginPersionDetails;
	@Override
	public void customerDetails() 
	{
		//DAO layer interface reference variable which is up casted By implementation class 
		
		
		List<BankCustomerDetails> allCustomerDetails = bankCustomerDAO.getAllCustomerDetails();
		//reference of Private details of attributes of class which have setters and getters and constructor
		BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
		//Asking user to enter details
		System.out.println("Enter Customer Name");
		String customerName = scan.nextLine();
		bankCustomerDetails.setName(customerName);
		//Validating the unique values weather present in database or not
		boolean emailStatus=true;
		while(emailStatus)
		{
			System.out.println("Enter Customer emailid");
			String emailid = scan.next();
			int emailCount =0;
			try 
			{
				//using for each loop to iterate over every object in the list
				for (BankCustomerDetails bankCustomerDetails2 : allCustomerDetails) 
				{
					if(bankCustomerDetails2.getEmailid().equals(emailid))
					{
						//if the entered email is present in database then the count increases
						emailCount++;
					}
				}
				//if the count > 0 means there unique value is already present in data base else it will set the value in database
				if(emailCount>0)
				{
					//throwing an exception if the value is already present
					//System.out.println("Already emailid Existed");
					throw new BankCustomerException("Already emailid existed");
				}
				else
				{
					bankCustomerDetails.setEmailid(emailid);
					emailStatus=false;
				}
			}
			//to handle the exception
			catch(BankCustomerException bankCustomerException)
			{
				System.out.println(bankCustomerException.getExceptionMessage());
			}
		}
		
		//Validating the unique values weather present in database or not
		while(true)
		{
			System.out.println("Enter the Customer Mobile Number ");
			long mobileNumber = scan.nextLong();
			int mobileCount=0;
			try
			{
			for (BankCustomerDetails bankCustomerDetails2 : allCustomerDetails) 
			{
				if(bankCustomerDetails2.getMobileNumber()==mobileNumber)
				{
					mobileCount++;
				}
			}
			if(mobileCount>0)
			{
				//System.out.println("Already Mobile Number Existed");
				throw new BankCustomerException("Already Mobile NUmber existed");
			}
			else
			{
				bankCustomerDetails.setMobileNumber(mobileNumber);
				break;
			}
			}
			catch(BankCustomerException bankCustomerException )
			{
				System.out.println(bankCustomerException.getExceptionMessage());
			}
		}
		
		//Validating the unique values weather present in database or not
		while(true)
		{
			int aadharCount=0;
			System.out.println("Enter the Customer Aadhar Number ");
			long aadharNumber = scan.nextLong();
			try
			{
				for (BankCustomerDetails bankCustomerDetails2 : allCustomerDetails) 
				{
					if(bankCustomerDetails2.getAadharNumber()==aadharNumber)
					{
						aadharCount++;
					}
				}
				if(aadharCount>0)
				{
					throw new BankCustomerException("Already aadhar Number existed");
				}
				else
				{
					bankCustomerDetails.setAadharNumber(aadharNumber);
					break;
				}
			}
			catch(BankCustomerException bankCustomerException )
			{
				System.out.println(bankCustomerException.getExceptionMessage());
			}
		}
		
		//Validating the unique values weather present in database or not
		while(true)
		{
			System.out.println("Enter the Customer PanCard Number (ABCDE1234Y)");
			String panNumber = scan.next();
			int panCount=0;
			try
			{
				for (BankCustomerDetails bankCustomerDetails2 : allCustomerDetails) 
				{
					if(bankCustomerDetails2.getPanNumber().equals(panNumber))
					{
						panCount++;
					}
				}
				if(panCount>0)
				{
					throw new BankCustomerException("Already Pan Card Number existed");
				}
				else
				{
					bankCustomerDetails.setPanNumber(panNumber);
					break;
				}
			}
			catch(BankCustomerException bankCustomerException )
			{
				System.out.println(bankCustomerException.getExceptionMessage());
			}
		
		}
		
		System.out.println("Enter the Customer date of birth(YYYY-MM-DD)");
		String dob = scan.next();
		bankCustomerDetails.setDateOfBirth(Date.valueOf(dob));
		
		
		System.out.println("Enter the Customer address");
		String address = scan.next();
		bankCustomerDetails.setAddress(address);
		
		
		System.out.println("Enter the Customer Gender");
		String gender = scan.next();
		bankCustomerDetails.setGender(gender);
		
		
		System.out.println("Enter the Customer age");
		int age = scan.nextInt();
		bankCustomerDetails.setAge(age);
		
		
		System.out.println("Enter the Customer Amount");
		double amount = scan.nextDouble();
		bankCustomerDetails.setAmount(amount);
		
		//inserting the User defined Data type(Object) into the data base which holds all the attributes of the customer
		bankCustomerDAO.insertBankCustomerDetails(bankCustomerDetails);
	}
	@Override
	public void customerLogin() 
	{
		System.out.println("Enter Customer emailid");
		String emailid = scan.next();
		System.out.println("Enter Customer  Pin ");
		int pin = scan.nextInt();
		loginPersionDetails = bankCustomerDAO.selectCustomerDetailsByUsingEmailidAndPin(emailid, pin);
	//	String displaycaptcha = randomCaptcha();
		//System.out.println("Enter the captcha: "+displaycaptcha);
		//String captcha = scan.next();
		if(loginPersionDetails!=null)//&& displaycaptcha.equals(captcha)
		{
			BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
			boolean isTrue=true;
			while(isTrue)
			{
				Random r = new Random();
				String capt ="";
				String [] a = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
						"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
						"0","1","2","3","4","5","6","7","8","9"};
				for(int i=0;i<6;i++)
				{
					int ind = r.nextInt(a.length);
					String cap = a[ind];
					capt = capt+cap;
				}
				System.out.println("Your Captha to Enter: "+capt);
				System.out.println("Enter the captcha");
				String captcha = scan.next();
				if(capt.equals(captcha))
				{
					isTrue=false;
					if(loginPersionDetails.getGender().equals("Male"))
					{
						System.out.println("Hello Mr."+loginPersionDetails.getName());
						bankCustomerOperation();
					}
					if(loginPersionDetails.getGender().equals("Female"))
					{
						System.out.println("Hello Miss."+loginPersionDetails.getName());
						bankCustomerOperation();
					}
				}
				else
				{
					System.out.println("Invalid Capthca");
				}
			}
		}
		else
		{
			System.out.println("Invalid email id or Pin");
		}
		
	}
	
//	public String randomCaptcha()
//	{
//		int length=6;
//		Random r = new Random();
//        String characters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
//        StringBuilder captcha = new StringBuilder();
//
//        for (int i = 0; i < length; i++) {
//            captcha.append(characters.charAt(r.nextInt(characters.length())));
//        }
//        
//        return captcha.toString();
//	}
	
	@Override
	public void bankCustomerOperation() 
	{
		System.out.println("Enter"
				+ "\n 1. For Credit"
				+ "\n 2. For Debit"
				+ "\n 3. For Check balance"
				+ "\n 4. For Check statement"
				+ "\n 5. For update Password"
				+ "\n 6. For Mobile to Mobile Transaction");
		
		switch(scan.nextInt())
		{
		case 1:
			System.out.println("Credit");
			credit();
			break;
		case 2:
			System.out.println("Debit");
			debit();
			break;
		case 3:
			System.out.println("Balance Check");
			balanceCheck();
			break;
		case 4:
			System.out.println("Statement");
			checkStatement();
			break;
		case 5:
			System.out.println("Update Password");
			break;
		case 6:
			System.out.println("Mobile to Mobile Transcation");
			break;
		case 7:
			System.out.println("Account Closing Request");
			break;
		default:
			System.out.println("invalid Option");
			break;
		}
	}
	
	@Override
	public void credit() {
		System.out.println("Enter Amount ");
		double userAmount = scan.nextDouble();
		if(userAmount>=0)
		{
			double databaseAmount = loginPersionDetails.getAmount();
			int accountNumber = loginPersionDetails.getAccountNumber();
			double balanceAmount = databaseAmount+userAmount;
			System.out.println(balanceAmount);
			if (bankCustomerDAO.updateBalanaceAmountByUsingAccountNumber(balanceAmount, accountNumber)) 
			{
				TransactionDetails  transactionDetails = new TransactionDetails();
				transactionDetails.setTransactionType("Credit");
				transactionDetails.setTransactionDate(LocalDate.now());
				transactionDetails.setTransactionTime(LocalTime.now());
				transactionDetails.setAccountNumber(accountNumber);
				transactionDetails.setBalanceAmount(balanceAmount);
				transactionDetails.setTrasactionAmount(userAmount);
				transactionDao.insertTrasactionDetails(transactionDetails);
				System.out.println("Amount Credited");
			}
			else
			{
				System.out.println("Server 404");
			}
		}
		else
		{
			System.out.println("Invalid Amount");
		}
		
	}
	TransactionDAO transactionDao = new TransactionDAOimpl();
	@Override
	public void debit()
	{
		System.out.println("Enter Amount ");
		double userAmount = scan.nextDouble();
		if(userAmount>=0)
		{
			double databaseAmount = loginPersionDetails.getAmount();
			int accountNumber = loginPersionDetails.getAccountNumber();
			if(userAmount<=databaseAmount)
			{
				double balanceAmount = databaseAmount-userAmount;
				System.out.println(balanceAmount);
				if (bankCustomerDAO.updateBalanaceAmountByUsingAccountNumber(balanceAmount, accountNumber)) 
				{
					TransactionDetails  transactionDetails = new TransactionDetails();
					transactionDetails.setTransactionType("Debit");
					transactionDetails.setTransactionDate(LocalDate.now());
					transactionDetails.setTransactionTime(LocalTime.now());
					transactionDetails.setAccountNumber(accountNumber);
					transactionDetails.setBalanceAmount(balanceAmount);
					transactionDetails.setTrasactionAmount(userAmount);
					transactionDao.insertTrasactionDetails(transactionDetails);
					System.out.println("Amount Debited");
				}
				else
				{
					System.out.println("Server 404");
				}
			}
			else
			{
				System.out.println("Insufficient balanace");
			}
		}
		else
		{
			System.out.println("Invalid Amount");
		}
	}
	@Override
	public void checkStatement() 
	{
		List<TransactionDetails> list = transactionDao.selectTransactionDetailsBasedOnAccountNumber(loginPersionDetails.getAccountNumber());
		if(!list.isEmpty())
		{
			System.out.println("Name: "+loginPersionDetails.getName());
			System.out.println("Account Number "+loginPersionDetails.getAccountNumber());
			list.forEach((transactionDetails)->{
				System.out.println("Transaction type :"+transactionDetails.getTransactionType());
				System.out.println("Transaction Date :"+transactionDetails.getTransactionDate());
				System.out.println("Transaction Time :"+transactionDetails.getTransactionTime());
				System.out.println("Transaction Amount :"+transactionDetails.getTrasactionAmount());
				System.out.println("Balance Amount :"+transactionDetails.getBalanceAmount());
				System.out.println("******-------*******-------*******");
			});
		}
		else
		{
			System.out.println("No Transaction details Found");
		}
	}
	@Override
	public void balanceCheck() 
	{
		double balanceAmount = loginPersionDetails.getAmount();
		System.out.println("Balance Amount In The Account: "+balanceAmount+"₹");
		
	}	
}
