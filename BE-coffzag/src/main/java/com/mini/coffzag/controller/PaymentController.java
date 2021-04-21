package com.mini.coffzag.controller;

import com.mini.coffzag.dto.PaymentRequestDto;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnPayment;
import com.mini.coffzag.response.ReturnUserInfo;
import com.mini.coffzag.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @GetMapping("/api/payments/userInfo")
    public ReturnUserInfo getUserInfo(@AuthenticationPrincipal User user) {
        return paymentService.getUserInfo(user);

    }

    @PostMapping("/api/payments")
    public ReturnMsg createPayment(@RequestBody PaymentRequestDto paymentRequestDto, @AuthenticationPrincipal User user) {
        return paymentService.createPayment(paymentRequestDto, user);
    }

    @DeleteMapping("/api/payments/orderList")
    public ReturnMsg deleteOrderList(@AuthenticationPrincipal User user) {
        return paymentService.deleteOrderList(user);
    }

    @GetMapping("/api/payments")
    public ReturnPayment getPaymentsList(@AuthenticationPrincipal User user) {
        return paymentService.getPaymentsList(user);
    }

}
