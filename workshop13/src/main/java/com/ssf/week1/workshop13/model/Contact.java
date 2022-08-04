package com.ssf.week1.workshop13.model;

import java.util.Random;

public class Contact {
    private String id;
    private String name;
    private String email;
    private String phone;

    public Contact() {
        this.id = generateId(8);
    }

    public Contact(String name, String email, String phone) {
        this.id = generateId(8);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String generateId(int numChars) {
        Random r = new Random();
        String id = "";
        while (id.length() < numChars) {
            id += Integer.toHexString(r.nextInt());
        }

        return id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    
}
