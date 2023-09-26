package com.vamsi.transactions.dto;

import java.sql.Date;

public class TransactionDTO {
	private Long userId;
	private Date date;
    private Date repayDate;
    private String description;
    private String mode;
    private String amount;
    private String debitCard;
    private String creditCard;
    private Boolean borrowedFromMe;
    private Boolean borrowedByMe;
    private String status;
    
	public TransactionDTO(Long userId, Date date, Date repayDate, String description, String mode, String amount,
			String debitCard, String creditCard, Boolean borrowedFromMe, Boolean borrowedByMe, String status) {
		super();
		this.userId = userId;
		this.date = date;
		this.repayDate = repayDate;
		this.description = description;
		this.mode = mode;
		this.amount = amount;
		this.debitCard = debitCard;
		this.creditCard = creditCard;
		this.borrowedFromMe = borrowedFromMe;
		this.borrowedByMe = borrowedByMe;
		this.status = status;
	}
	
    @Override
	public String toString() {
		return "TransactionDTO [userId=" + userId + ", date=" + date + ", repayDate=" + repayDate + ", description="
				+ description + ", mode=" + mode + ", amount=" + amount + ", debitCard=" + debitCard + ", creditCard="
				+ creditCard + ", borrowedFromMe=" + borrowedFromMe + ", borrowedByMe=" + borrowedByMe + ", status="
				+ status + "]";
	}

    public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getRepayDate() {
		return repayDate;
	}
	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDebitCard() {
		return debitCard;
	}
	public void setDebitCard(String debitCard) {
		this.debitCard = debitCard;
	}
	public String getCreditCard() {
		return creditCard;
	}
	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}
	public Boolean getBorrowedFromMe() {
		return borrowedFromMe;
	}
	public void setBorrowedFromMe(Boolean borrowedFromMe) {
		this.borrowedFromMe = borrowedFromMe;
	}
	public Boolean getBorrowedByMe() {
		return borrowedByMe;
	}
	public void setBorrowedByMe(Boolean borrowedByMe) {
		this.borrowedByMe = borrowedByMe;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
