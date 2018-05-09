package com.paywhiz.auth.web;

import java.io.IOException;
import java.security.Principal;
import java.sql.*;
import java.util.Calendar;

import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paywhiz.auth.model.Transaction;
import com.paywhiz.auth.model.User;
import com.paywhiz.auth.service.SecurityService;
import com.paywhiz.auth.service.UserService;
import com.paywhiz.auth.validator.UserValidator;

//@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autologin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "login";
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String welcome(Model model) {
        return "welcome";
    }
    
    @RequestMapping(value = {"/secured"}, method = RequestMethod.GET)
    public String sec(Model model) {
        return "welcome";
    }
   
    @RequestMapping(value = {"/ever"}, method = RequestMethod.GET)
    public String ever(Model model) {
    	
    	model.addAttribute("udetails", userService.findEver().getWallet());
        return "details"; //userService.findEver().toString();
    }
    
    
    @RequestMapping(value = "/purchase", method = RequestMethod.GET)
    public String details(Model model, Principal principal) {
        model.addAttribute("wallet", new Transaction());
        User u1 = userService.findByUsername(principal.getName());
        model.addAttribute("udetails", u1.getWallet());

        return "purchase";
    }
    
    
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    public String details(@ModelAttribute("wallet") Transaction user, BindingResult bindingResult, Model model, Principal principal)throws IOException
    {
    	model.addAttribute("wallet", user);
    	Calendar calendar = Calendar.getInstance();
    	java.util.Date currentDate = calendar.getTime();
    	java.sql.Date transDate = new java.sql.Date(currentDate.getTime());
		//System.out.print(user.getId()+" "+user.getPurchaseAmt()+" "+principal.getName()+" "+transDate);
    	userService.updateWallet(principal.getName(), user.getPurchaseAmt(), user.getCategory());
    	//System.out.print(user.getId()+" "+user.getPurchaseAmt()+" "+principal.getName()+" "+transDate);

    	return "redirect:/purchase";
    }
    
    /*
    @RequestMapping(value = "/purchase", method = RequestMethod.POST)
    //public String details(@ModelAttribute("wallet") User user, BindingResult bindingResult,Model model)throws IOException
    //public String details( Model model, User user, Principal principal)
    public String details( Model model, Transaction user, Principal principal)
    {
    	model.addAttribute("wallet", user);
    	userService.updateWallet(principal.getName(), user.getPurchaseAmt(), user.getCategory());
    	System.out.print(user.getId()+" "+user.getPurchaseAmt()+" "+principal.getName());

    	return "redirect:/purchase";
    }
    */
    
    
    @RequestMapping(value = "/refill", method = RequestMethod.GET)
    public String refill(Model model, Principal principal) {
        //model.addAttribute("wallet", new User());
        User u1 = userService.findByUsername(principal.getName());
        model.addAttribute("udetails", u1.getWallet());

        return "refill";
    }
    
    @RequestMapping(value = "/refill", method = RequestMethod.POST)
    public String refill( Model model, User user, Principal principal)
    {
    	model.addAttribute("wallet", user);
    	userService.addWallet(principal.getName(), user.getWallet());
    	System.out.print(user.getId()+" "+user.getWallet()+" "+principal.getName());

    	return "redirect:/refill";
    }
    
}
