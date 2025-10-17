package com.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.bank.DAO.AdminDAOimp;
import com.bank.DAO.AdminDao;
import com.bank.DAO.BankCustomerDAO;
import com.bank.DAO.BankCustomerDAOImpl;
import com.bank.model.BankCustomerDetails;

public class AdminServiceImpl implements AdminService
{
	Scanner scan = new Scanner(System.in);
	AdminDao adminDao = new AdminDAOimp();
	BankCustomerDAO bankCustomerDao = new BankCustomerDAOImpl();
	
	private List<BankCustomerDetails> allPendingDetailsList;
	@Override
	public void adminLogin() 
	{
		System.out.println("Enter Admin Email id");
		String emailid = scan.next();
		System.out.println("Enter admin password");
		String password = scan.next();
		if(adminDao.selectAdminDetailsByUsingEmailidAndPassword(emailid, password))
		{
			System.out.println("Enter \n 1.To Get All Account Request Details "
									+ "\n 2.To Get All User Details "
									+ "\n 3.To Get All Account closing requestes");
			switch(scan.nextInt())
			{
			case 1:
				System.out.println("All Account Request Details");
				allPendingDetails();
				break;
			case 2:
				System.out.println("All User Details");
				allUserDetails();
				break;
			case 3:
				System.out.println("All Account Closing Request Details");
			
				break;
			default:
				System.out.println("Invalid Request");
				break;
			}
		}
		
	}
	@Override
	public void allPendingDetails() 
	{
		List<BankCustomerDetails> allCustomerDetails =bankCustomerDao.getAllCustomerDetails();
		
		List<BankCustomerDetails> allPendingDetailsList = new ArrayList<BankCustomerDetails>();
	
		for(BankCustomerDetails bankCustomerDetails : allCustomerDetails)
		{
			
			if(bankCustomerDetails.getStatus().equalsIgnoreCase("Pending"))
			{
				BankCustomerDetails bankCustomerDetails2 = new BankCustomerDetails();
				bankCustomerDetails2.setName(bankCustomerDetails2.getName());
				bankCustomerDetails2.setEmailid(bankCustomerDetails2.getEmailid());
				bankCustomerDetails2.setId(bankCustomerDetails2.getId());
				allPendingDetailsList.add(bankCustomerDetails);
				
				//Give all the pending details of user
				//int indexOf = allCustomerDetails.indexOf(bankCustomerDetails)+1;
				int indexOf = allCustomerDetails.indexOf(bankCustomerDetails)+1;
				System.out.println("S.No: "+indexOf);
				System.out.println("Customer Name: "+bankCustomerDetails.getName());
				System.out.println("Customer EmailId: "+bankCustomerDetails.getEmailid());
				System.out.println("Customer Mobile Number: "+bankCustomerDetails.getMobileNumber());
				System.out.println("Customer Status: "+bankCustomerDetails.getStatus());
				System.out.println("*****-----*****-----*****");
			}
		}
		System.out.println("Enter S.No To select the Customer Details");
		//int index = scan.nextInt();
		//System.out.println(allCustomerDetails.get(index-1));
		BankCustomerDetails adminSelectedObjects = allPendingDetailsList.get(scan.nextInt()-1);
		System.out.println(adminSelectedObjects);
		acceptPendingDetails(adminSelectedObjects);
		System.out.println("Enter \n 1.Accept \n 2.Delete");
		switch(scan.nextInt())
		{
		case 1:
			acceptPendingDetails(adminSelectedObjects);
			break;
		case 2:
			deletePendingDetails(adminSelectedObjects);
			break;
		default:
			break;
		}
		
		
	}
	@Override
	public void allUserDetails() 
	{
		List<BankCustomerDetails> allCustomerDetails =bankCustomerDao.getAllCustomerDetails();
		allCustomerDetails.forEach((customerDetails)->{
			int num = allCustomerDetails.indexOf(customerDetails)+1;
			System.out.println("S.No: "+num);
			System.out.println("Customer Name: "+ customerDetails.getName());
			System.out.println("Customer Emailid: "+customerDetails.getEmailid());
			long l=customerDetails.getMobileNumber();
			String s =""+l;
			System.out.println("Customer Mobile Number: "+s.substring(0, 2)+"****"+s.substring(6, 10));
			long l1 = customerDetails.getAadharNumber();
			String s2 = ""+l1;
			System.out.println("Customer Aadhar Number: "+s2.substring(0,2)+"******"+s2.substring(8,12));
			System.out.println("*****-----*****-----*****");
		});
		
	}
	
	@Override 
    public void acceptPendingDetails(BankCustomerDetails bankCustomerDetails)
    {
    	Random random=new Random();
    	int accountnumber=random.nextInt(10000000);
    	System.out.println(accountnumber);
    	if(accountnumber<1000000)
    	{
    		accountnumber+=1000000;
    	}
    	System.out.println(accountnumber);
    	
    	int pin=random.nextInt(10000);
    	if (pin<1000)
    	{
			pin+=1000;
		}
    	bankCustomerDetails.setAccountNumber(accountnumber);
    	bankCustomerDetails.setPin(pin);
    	bankCustomerDao.updateAccountNumberAndPinByUsingId(bankCustomerDetails);
    	//System.out.println(pin);
    }
	
	@Override
	public void deletePendingDetails(BankCustomerDetails bankCustomerDetails) 
	{
		bankCustomerDao.deleteCustomerDetailsByUsingId(bankCustomerDetails);
	}


	

}
