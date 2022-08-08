package com.ssf.week1.shoppingcart.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssf.week1.shoppingcart.model.Cart;
import com.ssf.week1.shoppingcart.model.CartItem;
import com.ssf.week1.shoppingcart.store.CartRepo;

@Controller
@RequestMapping("/cart")
public class CartController {
    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @Autowired
    CartRepo cartRepo;

    private void setupRepo(String username) {
        cartRepo.setUsername(username);
        cartRepo.setFile(new File("/Users/rahillah/Desktop/SSF/ssf/shoppingcart/db"));
    }

    // get mapping with path variable for loading cart
    @GetMapping
    public String showUserCart(@RequestParam String username, Model model) {
        logger.info("username to load >> " + username);
        setupRepo(username);
        Cart currentCart = cartRepo.load();
        model.addAttribute("cart", currentCart);

        return "shoppingcart";
    }
    
    // post mapping for the form (adding)
    @PostMapping
    public String addCartItem(@ModelAttribute("cart") Cart c,
                             Model model) {
        setupRepo(c.getUsername());
        List<String> checkDuplicates = new ArrayList<>();

        Cart currentCart = cartRepo.load();
        CopyOnWriteArrayList<CartItem> currentItemList = currentCart.getCartItems();
        for (CartItem item : currentItemList) {
            checkDuplicates.add(item.getItemName());
        }

        if (currentItemList.size() > 0) {
            int index = 0;
            for (CartItem item : currentItemList) {
                if (item.getItemName().equals(c.getItemName())) {
                    CartItem i = new CartItem();
                    i.setItemName(c.getItemName());
                    i.setItemQuantity(item.getItemQuantity()+1);
                    currentItemList.set(index,i);
                    c.setCartItems(currentItemList);
                } else {
                    if (!checkDuplicates.contains(c.getItemName())) {
                        CartItem i = new CartItem();
                        i.setItemName(c.getItemName());
                        i.setItemQuantity(1);
                        currentItemList.add(i);
                        checkDuplicates.add(c.getItemName());
                        c.setCartItems(currentItemList);
                    }
                }
                index++;
            }
        } else {
            CartItem i = new CartItem();
            i.setItemName(c.getItemName());
            i.setItemQuantity(1);
            currentItemList.add(i);
            checkDuplicates.add(c.getItemName());
            c.setCartItems(currentItemList);
        }

        cartRepo.save(c);
        Cart newCart = cartRepo.load();
        model.addAttribute("cart", newCart);

        return "shoppingcart";
    }

    // getmapping for deleting
    @GetMapping("/delete/{cartItemId}")
    public String deleteCartItem(@ModelAttribute("cart") Cart cart, 
                                 @PathVariable String cartItemId,
                                 @RequestParam String username, Model model) {
        setupRepo(username);
        Cart c = this.cartRepo.load();
        this.cartRepo.delete(cartItemId, c);
        Cart newCart = cartRepo.load();
        model.addAttribute("cart", newCart);

        return "shoppingcart";
    }

    // getmapping for sorting up
    @GetMapping("/sortUp/{cartItemId}")
    public String sortUpItem(@ModelAttribute("cart") Cart cart, 
                             @PathVariable String cartItemId,
                             @RequestParam String username, Model model) {
        setupRepo(username);
        Cart c = this.cartRepo.load();
        int index = 0;
        for (CartItem item : c.getCartItems()) {
            if (item.getId().equals(cartItemId)) {
                CartItem itemAbove = c.getCartItems().get(index-1);
                c.getCartItems().set(index-1, item);
                c.getCartItems().set(index, itemAbove);
            }
            index++;
        }
        cartRepo.save(c);
        model.addAttribute("cart", c);

        return "shoppingcart";
    }

    // getmapping for editing item
    @GetMapping("/edit/{cartItemId}")
    public String editCartItem(@ModelAttribute("cart") Cart cart,
                               @PathVariable String cartItemId,
                               @RequestParam String username, Model model) {
        setupRepo(username);
        Cart c = this.cartRepo.load();
        Cart editCart = new Cart(username);
        for (CartItem item : c.getCartItems()) {
            if (item.getId().equals(cartItemId)) {
                editCart.setItemName(item.getItemName());
                editCart.setUsername(username);
                editCart.setEditCartId(item.getId());
                editCart.setCartItems(c.getCartItems());
            }
        }
        model.addAttribute("cart", editCart);
        
        return "shoppingcart";
    }

    // postmapping for updating item
    @PostMapping("/update")
    public String updateCartItem(@ModelAttribute("cart") Cart cart, Model model) {
        setupRepo(cart.getUsername());
        Cart c = this.cartRepo.load();
        int index = 0;
        for (CartItem item : c.getCartItems()) {
            if (item.getId().equals(cart.getEditCartId())) {
                item.setItemName(cart.getItemName());
                c.getCartItems().set(index,item);
            }
            index++;
        }
        cartRepo.save(c);
        model.addAttribute("cart",c);

        return "shoppingcart";
    }
}
