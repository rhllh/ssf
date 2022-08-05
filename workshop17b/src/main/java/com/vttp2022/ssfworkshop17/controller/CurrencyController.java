package com.vttp2022.ssfworkshop17.controller;

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

import com.vttp2022.ssfworkshop17.model.Currency;
import com.vttp2022.ssfworkshop17.service.CurrencyService;

/*
 * TO DO
 * deploy to heroku
 */
@Controller
@RequestMapping(path="/")
public class CurrencyController {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    
    @Autowired
    CurrencyService service;

    @GetMapping
    public String getCurrencies(Model model) {
        //logger.info("before getAllCurrencies()");
        List<Currency> currencyList = service.getAllCurrencies();
        //logger.info("out of getAllCurrencies()");

        model.addAttribute("currencies", currencyList);
        
        return "index";
    }

    @PostMapping("convert")
    public String convertCurrency(@RequestBody MultiValueMap<String,String> form,
                                Model model) {
        String fromCurrency = form.getFirst("fromCurrency");
        String toCurrency = form.getFirst("toCurrency");
        Integer amount = Integer.parseInt(form.getFirst("amount"));

        Double convertedAmount = service.convertCurrency(amount, fromCurrency, toCurrency);

        // logger.info("fromCurrency >> " + fromCurrency);
        // logger.info("toCurrency >> "+ toCurrency);
        // logger.info("initial >> "+ amount);
        // logger.info("final >> " + convertedAmount);
        
        model.addAttribute("fromCurrency", fromCurrency);
        model.addAttribute("toCurrency", toCurrency);
        model.addAttribute("initialAmount", amount);
        model.addAttribute("finalAmount", convertedAmount);
        
        return "converted";
    }

}
