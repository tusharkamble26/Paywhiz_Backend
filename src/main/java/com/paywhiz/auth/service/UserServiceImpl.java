package com.paywhiz.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.paywhiz.auth.model.Transaction;
import com.paywhiz.auth.model.User;
import com.paywhiz.auth.repository.RoleRepository;
import com.paywhiz.auth.repository.TransRepository;
import com.paywhiz.auth.repository.UserRepository;

import java.util.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private TransRepository transRepo;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(roleRepository.findAll()));
        userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

	@Override
	public User findEver() {
		// TODO Auto-generated method stub
		return userRepository.findEver();
	}
	
	@Override
	public void setWalletU(String w) {
		// TODO Auto-generated method stub
		//return (int) w;
	}

	@Override
	public String toString(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWallet(String name, float w, String category) {
		// TODO Auto-generated method stub
		//System.out.print("Update Wallet Method "+name+" "+w+" "+category+" "+transDate);
		User u1 = userRepository.findByUsername(name);
		Transaction t = new Transaction();
		
		if(w<u1.getWallet())
		{
			t.setPurchaseAmt(w);
			t.setUserID(u1.getId());
			t.setCategory(category);
			//t.setTransDate(transDate);
			transRepo.save(t);
			w=u1.getWallet()-w;
			u1.setWallet(w);
			userRepository.save(u1);
		}
		return;		
	}
	
	/*
	@Override
	public void updateWallet(String name, float w) {
		// TODO Auto-generated method stub
		User u1 = userRepository.findByUsername(name);
		if(w<u1.getWallet())
		{
			w=u1.getWallet()-w;
			u1.setWallet(w);
			userRepository.save(u1);
		}
		return;
		
	}
	*/
    
	@Override
	public void addWallet(String name, float w) {
		// TODO Auto-generated method stub
		User u1 = userRepository.findByUsername(name);
		w = w+ u1.getWallet();
		u1.setWallet(w);
		userRepository.save(u1);
		
	}
	@Override
	public void addWallet(User user) {
		// TODO Auto-generated method stub
		//User u1 = userRepository.findByUsername(name);
		//w = w+ u1.getWallet();
		//u1.setWallet(w);
		userRepository.save(user);
		
	}

	@Override
	public void updateWallet(String name, float w) {
		// TODO Auto-generated method stub
		
	}
	
	
    
}
