package com.bank.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAOimp implements AdminDao
{
	private static final String url = "jdbc:mysql://localhost:3306/teca_66_advance_java_project?user=root&password=root";
	private static final String admin_login ="select * from admindetails where email_id = ? and password = ?";

	@Override
	public boolean selectAdminDetailsByUsingEmailidAndPassword(String emailid, String password) 
	{
		try 
		{
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement preparedStatement = connection.prepareStatement(admin_login);
			preparedStatement.setString(1, emailid);
			preparedStatement.setString(2, password);
			ResultSet resultSet =preparedStatement.executeQuery();
			if(resultSet.next())
			{
				//System.out.println("Login Successful");
				return true;
			}
			else
			{
				return false;
				//System.out.println("Invalid EmailId or Password");
			}
		}
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
