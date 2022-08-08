package com.ssf.week1.shoppingcart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssf.week1.shoppingcart.model.Cart;

@Controller
@RequestMapping("/")
public class IndexController {
    
    @GetMapping
    public String showIndexPage(Model model) {
        Cart c = new Cart("");
        model.addAttribute("cart", c);
        
        return "shoppingcart";
    }
}
