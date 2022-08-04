package com.ssf.week2.workshop16b.model;

import org.springframework.stereotype.Component;

@Component
public class JsonInput {
    private String jsonInputString;

    public String getJsonInputString() {
        return jsonInputString;
    }

    public void setJsonInputString(String jsonInputString) {
        this.jsonInputString = jsonInputString;
    }
}
