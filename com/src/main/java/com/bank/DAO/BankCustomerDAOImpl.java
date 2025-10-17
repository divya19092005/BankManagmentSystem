package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.BankCustomerDetails;

public class BankCustomerDAOImpl implements BankCustomerDAO
{
	//creating only once and it should not be accessible to outside
	private static final String insert_customer_details =
			"insert into bankcustomerdetails(Name, Emailid, Mobile_Number, Aadhar_Number, Pan_Number, Date_Of_Birth, Address, Amount, Age, Gender,status)"
			+ " values(?,?,?,?,?,?,?,?,?,?,?)";
	//URL for database connection
	private static final String url = "jdbc:mysql://localhost:3306/teca_66_advance_java_project?user=root&password=root";
	
	//selection queryy
	private static final String select_All = "select * from bankcustomerdetails";
	
	private static final String update_pin_accountnumber = "update bankcustomerdetails set Account_Number=?,Pin =?,status=? where Id=?";
	
	private static final String delete_customer_details = "delete from bankcustomerdetails where Id =?";
	
	private static final String customer_login=" select * from bankcustomerdetails where Emailid =? And Pin=?";
	
	private static final String update_Amount = "update bankcustomerdetails set Amount =? where Account_Number =?";
	@Override
	public void insertBankCustomerDetails(BankCustomerDetails bankCustomerDetails) 
	{
		 try 
		 {
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1, bankCustomerDetails.getName());
			preparedStatement.setString(2, bankCustomerDetails.getEmailid());
			preparedStatement.setLong(3, bankCustomerDetails.getMobileNumber());
			preparedStatement.setLong(4, bankCustomerDetails.getAadharNumber());
			preparedStatement.setString(5, bankCustomerDetails.getPanNumber());
			preparedStatement.setDate(6, bankCustomerDetails.getDateOfBirth());
			preparedStatement.setString(7, bankCustomerDetails.getAddress());
			preparedStatement.setDouble(8, bankCustomerDetails.getAmount());
			preparedStatement.setInt(9, bankCustomerDetails.getAge());
			preparedStatement.setString(10, bankCustomerDetails.getGender());
			preparedStatement.setString(11, "Pending");
			int res = preparedStatement.executeUpdate();
			if(res>0)
			{
				System.out.println("Registered Successfully...");
			}
			else
			{
				System.out.println("Registration already done");
			}
		 } 
		 catch (SQLException e) 
		 {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	}

	@Override
	public List<BankCustomerDetails> getAllCustomerDetails() 
	{
		//selection
		try
		{
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(select_All);
			ResultSet resultSet = preparedStatement.executeQuery();
			List<BankCustomerDetails> listOfBankCustomerDetails = new ArrayList<BankCustomerDetails>();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
					BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
					bankCustomerDetails.setId(resultSet.getInt("Id"));
					bankCustomerDetails.setName(resultSet.getString("Name"));
					bankCustomerDetails.setEmailid(resultSet.getString("Emailid"));
					bankCustomerDetails.setAadharNumber(resultSet.getLong("Aadhar_Number"));
					bankCustomerDetails.setMobileNumber(resultSet.getLong("Mobile_Number"));
					bankCustomerDetails.setPanNumber(resultSet.getString("Pan_Number"));
					bankCustomerDetails.setStatus(resultSet.getString("status"));
					listOfBankCustomerDetails.add(bankCustomerDetails);
				}
				return listOfBankCustomerDetails;
			}
			else
			{
				return null;
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			return null;
		}
		
		
		
	}

	@Override
	public void updateAccountNumberAndPinByUsingId(BankCustomerDetails bankCustomerDetails) 
	{
		try 
		{
	    	Connection connection=DriverManager.getConnection(url);
	    	PreparedStatement preparedStatement=connection.prepareStatement(update_pin_accountnumber);
	    	preparedStatement.setInt(1,bankCustomerDetails.getAccountNumber());
	    	preparedStatement.setInt(2,bankCustomerDetails.getPin());
	    	preparedStatement.setInt(4,bankCustomerDetails.getId());
	    	preparedStatement.setString(3,"Accepted");
	    	int result=preparedStatement.executeUpdate();
	    	if(result>0)
	    	{
	    		System.out.println("Updated");
	    	}
	    	else 
	    	{
	    		System.out.println("Not Updated");
	    	}
	    }
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }

		
	}

	@Override
	public void deleteCustomerDetailsByUsingId(BankCustomerDetails bankCustomerDetails) 
	{
		try 
		{
	    	Connection connection=DriverManager.getConnection(url);
	    	PreparedStatement preparedStatement=connection.prepareStatement(delete_customer_details);
	    	preparedStatement.setInt(1,bankCustomerDetails.getId());
	    	int result=preparedStatement.executeUpdate();
	    	if(result>0)
	    	{
	    		System.out.println("Deleted");
	    	}
	    	else 
	    	{
	    		System.out.println("Not Deleted");
	    	}
	    }
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    }
		
	}

	@Override
	public BankCustomerDetails selectCustomerDetailsByUsingEmailidAndPin(String emailid, int pin) 
	{
		try 
		{
	    	Connection connection=DriverManager.getConnection(url);
	    	PreparedStatement preparedStatement=connection.prepareStatement(customer_login);
	    	preparedStatement.setString(1,emailid);
	    	preparedStatement.setInt(2, pin);
	    	ResultSet resultSet = preparedStatement.executeQuery();
	    	if(resultSet.next())
	    	{
	    		BankCustomerDetails bankCustomerDetails = new BankCustomerDetails();
	    		bankCustomerDetails.setName(resultSet.getString("Name"));
	    		bankCustomerDetails.setGender(resultSet.getString("Gender"));
	    		bankCustomerDetails.setAmount(resultSet.getDouble("Amount"));
	    		bankCustomerDetails.setAccountNumber(resultSet.getInt("Account_Number"));
	    		return bankCustomerDetails;
	    	}
	    	else 
	    	{
	    		return null;
	    	}
	    }
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    	return null;
	    }
	}

	@Override
	public boolean updateBalanaceAmountByUsingAccountNumber(double amount, int accountNumber) 
	{
		try 
		{
	    	Connection connection=DriverManager.getConnection(url);
	    	PreparedStatement preparedStatement=connection.prepareStatement(update_Amount);
	    	preparedStatement.setDouble(1,amount);
	    	preparedStatement.setInt(2, accountNumber);
	    	int result = preparedStatement.executeUpdate();
	    	if(result>=0)
	    	{
	    		return true;
	    	}
	    	else
	    		return false;
	    	
	    }
	    catch(SQLException e)
	    {
	    	e.printStackTrace();
	    	return false;
	    }
	}

	
}
