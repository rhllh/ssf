package com.vttp2022.ssfworkshop17b.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vttp2022.ssfworkshop17b.model.Weather;
import com.vttp2022.ssfworkshop17b.service.WeatherService;

@Controller
@RequestMapping("/weather")
public class WeatherController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherController.class);
    
    @Autowired
    private WeatherService service;

    @GetMapping
    public String getWeather(@RequestParam(required=true) String city,
                             Model model) {
        logger.info("inside getWeather()");

        Optional<Weather> opt = service.getWeather(city);

        logger.info("opt is empty? >> " + opt.isEmpty());
        if (opt.isEmpty()) {
            return "weather";
        }

        Weather w = opt.get();
        model.addAttribute("weather", opt.get());
        
        return "weather";
    }
}
