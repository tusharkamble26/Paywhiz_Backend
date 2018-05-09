package com.paywhiz.auth.service;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.paywhiz.auth.model.Transaction;
import com.paywhiz.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
    
    
    public User findEver();

	String toString(User user);

	void setWalletU(String  w);
	
	void updateWallet(String name, float w);
	
	void addWallet(String name, float w);

	void updateWallet(String name, float w, String category);

	void addWallet(User user);
	
}
