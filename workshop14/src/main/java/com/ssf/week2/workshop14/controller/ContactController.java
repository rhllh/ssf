package com.ssf.week2.workshop14.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.week2.workshop14.model.Contact;
import com.ssf.week2.workshop14.service.ContactsRedis;

@Controller
@RequestMapping("/")
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    ContactsRedis service;

    @Autowired
    ApplicationArguments appArgs;
    
    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact());

        return "contact";
    }

    @PostMapping("/submit")
    public String showContact(@ModelAttribute("contact") Contact c,
                              Model model) {
        // save to database
        service.save(c);

        // add attribute to model
        model.addAttribute("contact",c);
        
        return "showContact";
    }

    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable String id, Model model) {
        
        // retrive object from database and assign to c
        // ContactsRedis object is already injected so can use it
        Contact c = service.findById(id);

        // add attribute to model so it can be displayed on view
        model.addAttribute("contact",c);
        
        return "showContact";
    } 
}
