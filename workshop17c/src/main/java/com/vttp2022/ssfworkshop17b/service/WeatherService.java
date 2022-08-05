package com.vttp2022.ssfworkshop17b.service;

import javax.annotation.PostConstruct;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

import com.vttp2022.ssfworkshop17b.model.Weather;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class WeatherService {
    private static final Logger logger = LoggerFactory.getLogger(WeatherService.class);
    
    private static String URL = "https://api.openweathermap.org/data/2.5/weather";

    public Optional<Weather> getWeather (String city){
        String apiKey = System.getenv("OPEN_WEATHER_MAP_KEY");
        String weatherUrl = UriComponentsBuilder.fromUriString(URL)
            .queryParam("q", city.replaceAll(" ", "+"))
            .queryParam("units", "metric")
            .queryParam("appid", apiKey)
            .toUriString();

        logger.info(
            ">>> Complete Weather URI API address  : "  + weatherUrl
        );

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        try{
            resp = template.getForEntity(weatherUrl, String.class);
            Weather w = Weather.create(resp.getBody());
            logger.info("w >> " + w.toString());
            return Optional.of(w);
        }catch(Exception e){
            logger.error(e.getMessage());
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
