package com.paywhiz.auth.web;

import java.io.IOException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.paywhiz.auth.model.Transaction;
import com.paywhiz.auth.model.User;
import com.paywhiz.auth.repository.RoleRepository;
import com.paywhiz.auth.repository.TransRepository;
import com.paywhiz.auth.repository.UserRepository;
import com.paywhiz.auth.service.SecurityService;
import com.paywhiz.auth.service.UserService;
import com.paywhiz.auth.validator.UserValidator;

@RestController
public class UserRestController {
    @Autowired
    private UserService userService;
    
    private String res;

    @Autowired
    private SecurityService securityService;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private TransRepository transRepo;

    @GetMapping(value = "/registration")
    public String registration(Principal principal) {
        return "registration";
    }
    

    @PostMapping(value = "/registration")
    public User registration(@RequestBody User user) {
    	System.out.println("welcome to register");
        userService.save(user);
        System.out.println(user.toString());

        securityService.autologin(user.getUsername(), user.getPasswordConfirm());

        return user;
    }

    
    @GetMapping(value = "/login")
    public String login(Principal principal) {

        return res;
    }
    
    
    
    @PostMapping(value="/login")
    public User login(@RequestBody User user) {
        
    	boolean res = securityService.autologin(user.getUsername(), user.getPassword());
    	if(res)
    	{
    		return userRepository.findByUsername(user.getUsername());
    	}
    	user = new User();
    	return user;
    }

    @GetMapping(value = {"/", "/welcome"}, produces=MediaType.APPLICATION_JSON_VALUE)
    public String welcome() {
        return "welcome";
    }
    
   
    
    @GetMapping(value = "/purchase", produces=MediaType.APPLICATION_JSON_VALUE)
    public User details(Principal principal) {
        
        User u1 = userService.findByUsername(principal.getName());
        //model.addAttribute("udetails", u1.getWallet());

        return u1;
    }
    
    
    @PostMapping(value = "/purchase")
    public Transaction details(@RequestBody Transaction user, BindingResult bindingResult, Model model, Principal principal)throws IOException
    {
    	//model.addAttribute("wallet", user);
    	userService.updateWallet(principal.getName(), user.getPurchaseAmt(), user.getCategory());
    	System.out.print(user.getId()+" "+user.getPurchaseAmt()+" "+principal.getName());
    	
    	return user;
    }
    

    
    @GetMapping(value="refill", produces=MediaType.APPLICATION_JSON_VALUE)
    public User refill(Principal principal) {
        //model.addAttribute("wallet", new User());
        User u1 = userService.findByUsername(principal.getName());
        //model.addAttribute("udetails", u1.getWallet());

        return u1;
    }
   
    
    @PostMapping(value = "/refill")
    public User refill( @RequestBody User user, Principal principal)
    {
    	userService.addWallet(principal.getName(), user.getWallet());
    	//userService.addWallet(user);
    	//System.out.print(user.getId()+" "+user.getWallet()+" "+principal.getName());
    	user = userService.findByUsername(principal.getName());
    	return user;
    }
    
    @GetMapping(value = "/analyze")
    public List<Transaction> analyze(Principal principal)
    {
    	User user = userService.findByUsername(principal.getName());
    	//Transaction t1 = transRepo.findByUserID(user.getId());
    	
    	List<Transaction> transData = transRepo.findByTransactionID(user.getId());
    	/*
    	System.out.println(transData instanceof List);
    	Gson gson = new Gson();
    	
    	System.out.println(gson.toJson(transData));
    	for(int i:gson.toJson(transData))	
    	{
    		System.out.println(gson.toJson(transData.get(i)));
    		System.out.println(gson.toJson(transData.get(i)) instanceof String);
    	}*/
    	return transData;
    }
    
    
    
}
