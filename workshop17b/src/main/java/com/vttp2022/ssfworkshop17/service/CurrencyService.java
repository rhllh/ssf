package com.vttp2022.ssfworkshop17.service;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.vttp2022.ssfworkshop17.model.Currency;

@Service
public class CurrencyService {
    private static final Logger logger = LoggerFactory.getLogger(CurrencyService.class);
    
    //private static final String URL = "https://api.currencyapi.com/v3/";
    private static final String URL = "https://api.frankfurter.app/";

    @Autowired
    RestTemplate template = new RestTemplate();
    
    @Value("${curr.conv.key}")
    private String apiKey;

    private boolean hasKey;

    @PostConstruct
    private void init() {
        hasKey = null != apiKey;
        logger.info("api key set? >> " + hasKey);
    }

    public List<Currency> getAllCurrencies() {
        //logger.info("Service >> inside getAllCurrencies()");

        String currencyUrl = UriComponentsBuilder.fromUriString(URL)
                            .path("currencies")
                            .toUriString();
        logger.info("url >> " + currencyUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;

        List<Currency> currencyList = new LinkedList<>();
        try {
            resp = template.getForEntity(currencyUrl, String.class);

            currencyList = Currency.create(resp.getBody());
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return currencyList;
    }

    public Double convertCurrency(Integer amount, String fromCurrency, String toCurrency) {
        //logger.info("Service >> inside convertCurrency()");

        String convertUrl = UriComponentsBuilder.fromUriString(URL)
                            .path("latest")
                            .queryParam("amount", amount)
                            .queryParam("from", fromCurrency)
                            .queryParam("to", toCurrency)
                            .toUriString();
        logger.info("url >> " + convertUrl);

        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = null;
        double result = 0;
        try {
            resp = template.getForEntity(convertUrl, String.class);
            //logger.info("resp body >> " + resp.getBody());

            result = Currency.convert(resp.getBody(), toCurrency);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

}
