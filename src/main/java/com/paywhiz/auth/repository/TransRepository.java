package com.paywhiz.auth.repository;

import java.util.List;

import javax.persistence.Table;

import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paywhiz.auth.model.Transaction;
import com.paywhiz.auth.model.User;

@Table(name="transaction")
public interface TransRepository extends JpaRepository<Transaction, Long> {
	
	//User findByuserID(Long UserID);
	Transaction findByUserID(Long UserID);
	
	  @Query("select t.category, sum(t.purchaseAmt) from Transaction t where userId = ?1 group by category")
	  //@Query("select concat(t.category,' : ',sum(t.purchaseAmt)) from Transaction t where userId=?1 group by category")
	  List<Transaction> findByTransactionID(Long UserID);
	  
	
}
