package com.mini.coffzag.controller;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Order;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @GetMapping("/api/order/{cartId}")
    public List<Order> hello(@PathVariable Cart cartId) {
        return orderRepository.findByCartId(cartId);
    }

//    @GetMapping("/api/order")
//    public List<Order> hello() {
//        return orderRepository.findAll();
//    }


    @PostMapping("/api/order/{coffeeId}")
    public void addOrder(@PathVariable Long coffeeId,
                         @RequestBody OrderRequestDto orderRequestDto,
                         @AuthenticationPrincipal User user) {

        System.out.println("user: " + user.getUserId());

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        System.out.println("cart: " + cart.getCartId());

        Order order = new Order(coffeeId, orderRequestDto.getOrderCnt());

        System.out.println(order);

        cart.getOrderList().add(order);

        System.out.println(order);

        orderRepository.save(order);
    }

    @PutMapping("/api/order/{coffeeId}")
    public void modifyOrder(@PathVariable Long coffeeId,
                            @RequestBody OrderRequestDto orderRequestDto,
                            @AuthenticationPrincipal User user) {

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        System.out.println(cart.getCartId());

//        Order order = orderRepository.findByCartId(cart.getCartId()).orElseThrow(
//                () -> new NullPointerException("해당하는 Order가 없습니다.")
//        );
//
//        order.update(coffeeId, orderRequestDto.getOrderCnt());

    }

}
