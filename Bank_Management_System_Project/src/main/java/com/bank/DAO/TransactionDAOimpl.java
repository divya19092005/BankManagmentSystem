package com.bank.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.bank.model.TransactionDetails;

public class TransactionDAOimpl implements TransactionDAO
{
	private static final String insert_transaction_details = 
			" insert into transactiondetails(Transaction_Type, Transaction_Date, Transaction_Time, Balance_Amount, Transaction_Amount, Account_Number) values(?,?,?,?,?,?)";
	private static final String url = "jdbc:mysql://localhost:3306/teca_66_advance_java_project?user=root&password=root";
	
	private static final String select_details = "select * from transactiondetails where Account_Number=?";
	
	List<TransactionDetails> list = new ArrayList<TransactionDetails>();
	@Override
	public void insertTrasactionDetails(TransactionDetails transactionDetails) 
	{
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement prepareStatement = connection.prepareStatement(insert_transaction_details);
			prepareStatement.setString(1, transactionDetails.getTransactionType());
			prepareStatement.setDate(2, Date.valueOf(transactionDetails.getTransactionDate()));
			prepareStatement.setTime(3, Time.valueOf(transactionDetails.getTransactionTime()));
			prepareStatement.setDouble(4, transactionDetails.getBalanceAmount());
			prepareStatement.setDouble(5, transactionDetails.getTrasactionAmount());
			prepareStatement.setInt(6, transactionDetails.getAccountNumber());
			
			int result = prepareStatement.executeUpdate();
			if(result>=0)
			{
				System.out.println("Transaction entered");
			}
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public List<TransactionDetails> selectTransactionDetailsBasedOnAccountNumber(int Account_Number) 
	{
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement prepareStatement = connection.prepareStatement(select_details);
			prepareStatement.setInt(1, Account_Number);
			ResultSet resultSet = prepareStatement.executeQuery();
			if(resultSet.isBeforeFirst())
			{
				while(resultSet.next())
				{
				TransactionDetails transactionDetails = new TransactionDetails(resultSet.getInt("Transaction_Id"),
						resultSet.getString("Transaction_Type"),
						resultSet.getDate("Transaction_Date").toLocalDate(),
						resultSet.getTime("Transaction_Time").toLocalTime(),
						resultSet.getDouble("Balance_Amount"),
						resultSet.getDouble("Transaction_Amount"),
						resultSet.getInt("Account_Number"));
				list.add(transactionDetails);
				}
				return list;
			}
			else
			{
				return null;
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	

}
