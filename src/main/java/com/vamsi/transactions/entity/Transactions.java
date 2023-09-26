package com.vamsi.transactions.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
//@SequenceGenerator(name = "transactions_id_seq", sequenceName = "transactions_id_seq", allocationSize = 1)
public class Transactions {
	@Id
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_id_generator")
    @GenericGenerator(name = "transaction_id_generator", strategy = "com.vamsi.transactions.util.CustomIdGenerator")
	private String id;
	@ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
	@Temporal(TemporalType.DATE)
    private Date date;
    @Temporal(TemporalType.DATE)
    private Date repayDate;
    private String description;
    private String amount;
    private String mode;
    
    private String debitCard;
    private String creditCard;
    private Boolean borrowedFromMe;
    private Boolean borrowedByMe;
    private String status;
    
	@Override
	public String toString() {
		return "Transactions [id=" + id + ", user=" + user + ", date=" + date + ", repayDate=" + repayDate
				+ ", description=" + description + ", amount=" + amount + ", mode=" + mode + ", debitCard=" + debitCard
				+ ", creditCard=" + creditCard + ", borrowedFromMe=" + borrowedFromMe + ", borrowedByMe=" + borrowedByMe
				+ ", status=" + status + "]";
	}

	

	public Transactions(String id, User user, Date date, Date repayDate, String description, String amount, String mode,
			String debitCard, String creditCard, Boolean borrowedFromMe, Boolean borrowedByMe, String status) {
		super();
		this.id = id;
		this.user = user;
		this.date = date;
		this.repayDate = repayDate;
		this.description = description;
		this.amount = amount;
		this.mode = mode;
		this.debitCard = debitCard;
		this.creditCard = creditCard;
		this.borrowedFromMe = borrowedFromMe;
		this.borrowedByMe = borrowedByMe;
		this.status = status;
	}

	public Transactions() {
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}
	
	

}
