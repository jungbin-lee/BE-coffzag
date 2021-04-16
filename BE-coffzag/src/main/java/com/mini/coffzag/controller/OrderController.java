package com.mini.coffzag.controller;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Order;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @PostMapping("/api/order/{coffeeId}")
    public void addOrder(@PathVariable Long coffeeId, @RequestBody OrderRequestDto orderRequestDto, @AuthenticationPrincipal User user) {

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 장바구니가 없습니다.")
        );

        Order order = new Order(coffeeId, orderRequestDto.getOrderCnt());
        cart.getOrderList().add(order);

        orderRepository.save(order);
    }

}
