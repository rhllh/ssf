package com.ssf.week1.shoppingcart.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.CopyOnWriteArrayList;

public class Cart {
    private String username;
    private String itemName;
    private String editCartId;
    private CopyOnWriteArrayList<CartItem> cartItems = new CopyOnWriteArrayList<>();

    public Cart(String name) {
        this.username = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getEditCartId() {
        return editCartId;
    }

    public void setEditCartId(String editCartId) {
        this.editCartId = editCartId;
    }

    public CopyOnWriteArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(CopyOnWriteArrayList<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public void add(CartItem item) {
        for (CartItem i : cartItems) {
            if (i.getItemName().equals(item.getItemName())) {
                return;
            }
        }
        cartItems.add(item);
    }
    
    public void load(InputStream is) throws IOException {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        CartItem item;
        while ((line = br.readLine()) != null) {
            if (line != null) {
                String[] cartItemArr = line.split(",");
                item = new CartItem();
                item.setItemName(cartItemArr[0]);
                item.setItemQuantity(Integer.parseInt(cartItemArr[1]));
                item.setId(cartItemArr[2]);
                cartItems.add(item);
            }
        }
        br.close();
        isr.close();
    }
}
