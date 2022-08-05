package com.vttp2022.ssfworkshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Currency {
    private static final Logger logger = LoggerFactory.getLogger(Currency.class);
    
    private String currencyName;
    private String currencySymbol;

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getCurrencySymbol() {
        return currencySymbol;
    }

    public void setCurrencySymbol(String currencySymbol) {
        this.currencySymbol = currencySymbol;
    }

    // public void addCurrency(String symbol, String name) {
    //     Currency c = new Currency();
    //     c.setCurrencyName(name);
    //     c.setCurrencySymbol(symbol);
    //     currencies.add(c);
    // }

    public static List<Currency> create(String json) throws IOException {
        List<Currency> cList = new LinkedList<>();

        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject allCurrencies = r.readObject();

            //logger.info("o >> " + allCurrencies.toString());

            for (String currency : allCurrencies.keySet()) {
                //logger.info("currency: " + currency);

                Currency c = new Currency();

                c.currencySymbol = currency;
                c.currencyName = allCurrencies.getString(currency);
                cList.add(c);
            }
        }
        return cList;
    }

    public static Double convert(String json, String toCurrency) throws IOException {
        Double convertedAmount = Double.valueOf(0);
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonObject convertedObj = r.readObject();
            JsonObject rates = convertedObj.getJsonObject("rates");
            logger.info("rates >> " + rates.toString());

            convertedAmount = Double.parseDouble(rates.get(toCurrency).toString());
            logger.info("convertedAmount >> " + convertedAmount);
        }
        return convertedAmount;
    }
    
}
