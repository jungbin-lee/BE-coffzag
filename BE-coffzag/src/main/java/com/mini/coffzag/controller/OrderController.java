package com.mini.coffzag.controller;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.Order;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/api/order")
    public List<Order> readOrder(@AuthenticationPrincipal User user) {
        return orderService.readOrder(user);
    }

    @PostMapping("/api/order/{coffeeId}")
    public Order createOrder(@PathVariable Long coffeeId,
                         @RequestBody OrderRequestDto orderRequestDto,
                         @AuthenticationPrincipal User user) {

        return orderService.createOrder(coffeeId, orderRequestDto, user);
    }

    @PutMapping("/api/order/{coffeeId}")
    public void updateOrder(@PathVariable Long coffeeId,
                            @RequestBody OrderRequestDto orderRequestDto,
                            @AuthenticationPrincipal User user) {

        orderService.updateOrder(coffeeId, orderRequestDto, user);
    }

    @DeleteMapping("/api/order/{coffeeId}")
    public void deleteOrder(@PathVariable Long coffeeId,
                            @AuthenticationPrincipal User user) {

        orderService.deleteOrder(coffeeId, user);
    }

}
