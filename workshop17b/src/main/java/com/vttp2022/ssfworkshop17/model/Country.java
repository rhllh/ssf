package com.vttp2022.ssfworkshop17.model;

import java.util.Random;

public class Country {
    private String countryName;
    private String currencyName;
    private String id;

    public Country() {
        this.id = generateId(8);
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }
    
    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public synchronized String generateId(int numChars) {
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numChars; i++) {
            sb.append(Integer.toHexString(r.nextInt()));
        }
        return sb.toString().substring(0,numChars);
    }
}
