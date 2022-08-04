package com.ssf.week1.workshop12.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.week1.workshop12.model.Generate;

@Controller
@RequestMapping(path="/")
public class NumberGeneratorController {
    private static final Logger logger = LoggerFactory
                        .getLogger(NumberGeneratorController.class);
    
    @GetMapping
    public String showIndex(Model model) {
        return "index";
    }
    
    @GetMapping(path="/form")
    public String getCount(Model model) {

        model.addAttribute("generate", new Generate());

        return "form";
    }

    @PostMapping(path="/result")
    public String showResult(@ModelAttribute("generate") Generate generate,
                             Model model) {
        
        int count = generate.getNumberCount();
        logger.info("CONTROLLER: count >> " + count);

        Generate g = new Generate(count);
        List<Integer> listOfNumbers = g.getListOfGeneratedNumbers();
        logger.info("CONTROLLER: numbers generated >> " + listOfNumbers.toString());

        model.addAttribute("listOfNumbers", listOfNumbers);

        return "result";
    }
}
