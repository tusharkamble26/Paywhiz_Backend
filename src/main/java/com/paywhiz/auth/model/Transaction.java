package com.paywhiz.auth.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class Transaction {
	
	@Column(name="id")
	private Long id;
	@Column(name="userId")
	private Long userID;
	@Column(name="category")
    private String category;
	//@Column(name="category")
    //private java.sql.Date transDate;
	@Column(name="purchaseAmt")
    private float purchaseAmt;
    
    
    public Transaction(Long id, Long userID, String category, float purchaseAmt) {
		super();
		this.id = id;
		this.userID = userID;
		this.category = category;
		this.purchaseAmt = purchaseAmt;
		//this.transDate= transDate;
	}
    
    public Transaction() {
		// TODO Auto-generated constructor stub
	}
    
    /*
	public java.sql.Date getTransDate() {
		return transDate;
	}

	public void setTransDate(java.sql.Date transDate) {
		
		this.transDate = transDate;
	}*/

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getUserID() {
		return userID;
	}
	public void setUserID(Long userID) {
		this.userID = userID;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPurchaseAmt() {
		return purchaseAmt;
	}
	public void setPurchaseAmt(float purchaseAmt) {
		this.purchaseAmt = purchaseAmt;
	}
	
	@Override
	public String toString()
	{
		return this.category+" "+this.purchaseAmt;
	}
	
	

}
