package com.ssf.week1.workshop13.controller;

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

import com.ssf.week1.workshop13.model.Contact;
import com.ssf.week1.workshop13.util.Contacts;

@Controller
@RequestMapping("/")
public class ContactController {
    private static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    Contacts cs;

    @Autowired
    ApplicationArguments appArgs;
    
    @GetMapping
    public String showIndex(Model model) {

        return "index";
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("contact", new Contact());

        return "form";
    }

    @PostMapping("/contact")
    public String showContact(@ModelAttribute("contact") Contact c,
                              Model model) {
        cs.saveContact(c, appArgs);
        
        return "contact";
    }

    @GetMapping("/contact/{id}")
    public String getContactById(@PathVariable String id, Model model) {
        logger.info("CONTROLLER: id >> " + id);
        Contact c = cs.getById(id, appArgs);
        model.addAttribute("contact", c);
        
        return "contact";
    } 
}
