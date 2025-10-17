package com.bank;

import java.util.Scanner;

import com.bank.service.AdminService;
import com.bank.service.AdminServiceImpl;
import com.bank.service.BankCustomerService;
import com.bank.service.BankCustomerServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	Scanner scan = new Scanner(System.in);
    	BankCustomerService bankCustomerService = new BankCustomerServiceImpl();
    	AdminService adminService = new AdminServiceImpl();
    	boolean status = true;
    	while(status)
    	{
    		System.out.println("Enter \n 1. For Customer Resgistration \n 2. For Customer login \n 3. For Admin login");
    		switch (scan.nextInt()) 
    		{
    		case 1:
    			System.out.println("Customer Registration");
    			bankCustomerService.customerDetails();
    			break;
    		case 2 :
    			System.out.println("Customer Login");
    			bankCustomerService.customerLogin();
    			break;
    		case 3:
    			System.out.println("Admin Login");
    			adminService.adminLogin();
    			break;
    		default:
    			System.out.println("Invalid Request");
    			break;
    		}
    		System.out.println("Dou You Want To Continue \n YES \n NO");
    		if(scan.next().equalsIgnoreCase("YES"))
    		{
    		
    			
    		}
    		else
    		{
    			System.out.println("Thank You");
    			status = false;
    		}
    	}
    	scan.close();
    	}
    
}
