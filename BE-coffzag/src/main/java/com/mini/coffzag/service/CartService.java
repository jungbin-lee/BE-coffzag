package com.mini.coffzag.service;


import com.mini.coffzag.dto.CartRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    private final CartRepository cartRepositroy;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    public CartService(CartRepository cartRepositroy, UserRepository userRepository, ProductRepository productRepository) {
        this.cartRepositroy = cartRepositroy;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    public Cart createCart(CartRequestDto cartRequestDto, Long userid, Long productid){



        User user = userRepository.findById(userid).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );

        Product product =productRepository.findById(productid).orElseThrow(
                ()-> new IllegalArgumentException("productError")
        );


        Cart cart = new Cart(product,user,cartRequestDto);
        cartRepositroy.save(cart);
        return cart;
    }

    public List<Cart> getCartList(Long userid){
        User user = userRepository.findById(userid).orElseThrow(
                ()-> new IllegalArgumentException("userError")
        );
        return cartRepositroy.findByUser(user);
    }

    @Transactional
    public Cart updateCart(Long cartid, CartRequestDto cartRequestDto){

        Cart cart=cartRepositroy.findById(cartid).orElseThrow(
                ()-> new IllegalArgumentException("Cartrequesterror")
        );
        cart.update(cartRequestDto);
        return cart;
    }

    public String deleteCart(Long cartid){

        try{
            Optional<Cart> cart=cartRepositroy.findById(cartid);
        }catch (IllegalArgumentException e){
            return "false";
        }
        cartRepositroy.deleteById(cartid);
        return "ture";

    }
}
