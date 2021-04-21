package com.mini.coffzag.controller;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnOrder;
import com.mini.coffzag.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/api/orders")
    public ReturnOrder readOrder(@AuthenticationPrincipal User user) {
        return orderService.readOrder(user);
    }

    @PostMapping("/api/orders/{coffeeId}")
    public ReturnOrder createOrder(@PathVariable Long coffeeId,
                         @RequestBody OrderRequestDto orderRequestDto,
                         @AuthenticationPrincipal User user) {
        return orderService.createOrder(coffeeId, orderRequestDto, user);
    }

    @PutMapping("/api/orders/{coffeeId}")
    public ReturnOrder updateOrder(@PathVariable Long coffeeId,
                            @RequestBody OrderRequestDto orderRequestDto,
                            @AuthenticationPrincipal User user) {
        return orderService.updateOrder(coffeeId, orderRequestDto, user);
    }

    @DeleteMapping("/api/orders/{coffeeId}")
    public ReturnMsg deleteOrder(@PathVariable Long coffeeId,
                                 @AuthenticationPrincipal User user) {
        return orderService.deleteOrder(coffeeId, user);
    }

}
