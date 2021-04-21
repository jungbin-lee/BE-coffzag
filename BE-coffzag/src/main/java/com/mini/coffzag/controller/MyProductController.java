package com.mini.coffzag.controller;

import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnMyProduct;
import com.mini.coffzag.service.MyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyProductController {
    private final MyProductService myProductService;

    // 찜하기
    @PostMapping("/api/myproducts/{coffeeId}")
    public ReturnMsg updateMyProduct(@PathVariable Long coffeeId, @AuthenticationPrincipal User user) {
        return myProductService.updateMyProduct(coffeeId, user);
    }

//     로그인한 user가 찜한 products만 response
    @GetMapping("/api/myproducts")
    public ReturnMyProduct allMyProducts(@AuthenticationPrincipal User user) {
        return myProductService.allMyProducts(user);
    }
}
