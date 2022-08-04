package com.ssf.week2.workshop17.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.week2.workshop17.model.Excuse;
import com.ssf.week2.workshop17.service.ExcuseService;

@Controller
@RequestMapping("/")
public class ExcuseController {
    private static final Logger logger = LoggerFactory.getLogger(ExcuseController.class);
    
    @Autowired
    ExcuseService service;

    @GetMapping
    public String getExcuses(Model model) {
        model.addAttribute("excuse", new Excuse());
        return "index";
    }

    @PostMapping("/generate")
    public String generateExcuse(@RequestBody MultiValueMap<String, String> form,
                                Model model) {
        logger.info("category from form >> " + form.getFirst("category"));
        logger.info("count from form >> " + form.getFirst("count"));

        String category = form.getFirst("category");
        int count = Integer.parseInt(form.getFirst("count"));

        List<String> excuseList = service.generateExcuse(category, count);

        //logger.info("line >> " + line);

        model.addAttribute("excuseList", excuseList);

        return "excuse";
    }
}
