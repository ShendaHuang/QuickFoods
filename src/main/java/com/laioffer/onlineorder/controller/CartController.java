package com.laioffer.onlineorder.controller;

import com.laioffer.onlineorder.entity.Cart;
import com.laioffer.onlineorder.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class CartController {

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    @ResponseBody
    public Cart getCart() {
        return cartService.getCart();
    }

    @RequestMapping(value = "/cart/remove/{orderitemId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public void removeItemFromCart(@PathVariable("orderitemId") int orderItemId) {
        cartService.removeItemFromCart(orderItemId);
    }
}
