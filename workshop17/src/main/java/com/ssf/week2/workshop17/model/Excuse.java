package com.ssf.week2.workshop17.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Excuse {
    private static final Logger logger = LoggerFactory.getLogger(Excuse.class);
    
    private String category;
    private int count;
    private String line;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
    }

    public static List<String> getLineFromBody(String json) throws IOException {
        String excuseString;
        List<String> excuseList = new ArrayList<>();
        try (InputStream is = new ByteArrayInputStream(json.getBytes())) {
            JsonReader r = Json.createReader(is);
            JsonArray arr = r.readArray();
            for (int i = 0; i < arr.size(); i++) {
                JsonObject excuse = arr.getJsonObject(i);
                excuseString = excuse.get("excuse").toString();
                logger.info("line >> " + excuseString);
                excuseList.add(excuseString);
            }
        }
        return excuseList;
    }
    
}
