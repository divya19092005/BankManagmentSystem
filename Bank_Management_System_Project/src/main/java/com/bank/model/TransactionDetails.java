package com.bank.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails
{
	//Transaction_Id, Transaction_Type, Transaction_Date, Transaction_Time, Balance_Amount, Transaction_Amount, Account_Number
	private int transactionId;
	private String transactionType;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private double balanceAmount;
	private double trasactionAmount;
	private int accountNumber;
	
	public TransactionDetails() {}
	
	public TransactionDetails(int transactionId, String transactionType, LocalDate transactionDate,
			LocalTime transactionTime, double balanceAmount, double trasactionAmount, int accountNumber) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.transactionTime = transactionTime;
		this.balanceAmount = balanceAmount;
		this.trasactionAmount = trasactionAmount;
		this.accountNumber = accountNumber;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public LocalDate getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}
	public LocalTime getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(LocalTime transactionTime) {
		this.transactionTime = transactionTime;
	}
	public double getBalanceAmount() {
		return balanceAmount;
	}
	public void setBalanceAmount(double balanceAmount) {
		this.balanceAmount = balanceAmount;
	}
	public double getTrasactionAmount() {
		return trasactionAmount;
	}
	public void setTrasactionAmount(double trasactionAmount) {
		this.trasactionAmount = trasactionAmount;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		return "TransactionDetails [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionDate=" + transactionDate + ", transactionTime=" + transactionTime + ", balanceAmount="
				+ balanceAmount + ", trasactionAmount=" + trasactionAmount + ", accountNumber=" + accountNumber + "]";
	}
	
	
}
