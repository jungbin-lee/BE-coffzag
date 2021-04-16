package com.mini.coffzag.controller;

import com.mini.coffzag.dto.CartRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.service.CartService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = {"/api/cart/{userid}/{productid}"})
    public String createCart(@PathVariable Long userid,
                             @PathVariable Long productid,
                             @RequestBody CartRequestDto cartRequestDto){

        Cart cart = cartService.createCart(cartRequestDto,userid,productid);
        return "success";
    }

    @GetMapping("/api/cart/{userid}")
    public List<Cart> getCartList(@PathVariable Long userid){

        return cartService.getCartList(userid);
    }

    @PutMapping("/api/cart/{cartid}")
    public Cart updateCart(@PathVariable Long cartid, @RequestBody CartRequestDto requestDto){

        return cartService.updateCart(cartid,requestDto);
    }

    @DeleteMapping("/api/cart/{cartid}")
    public String deleteCart(@PathVariable Long cartid){
        return cartService.deleteCart(cartid);
    }

}