package com.mini.coffzag.controller;

import com.mini.coffzag.dto.MyProductRequestDto;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnMyProduct;
import com.mini.coffzag.service.MyProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MyProductController {
    private final MyProductService myProductService;

    //    찜하기
    @PostMapping("/api/myproducts/{coffeeId}")
    public ReturnMyProduct addMyProduct(@PathVariable Product coffeeId, @RequestBody MyProductRequestDto productDto, @AuthenticationPrincipal User user){
        return  myProductService.addMyProduct(coffeeId,user,productDto);
    }



//    //찜하기 Get
//    @GetMapping("/api/product/{userid}")
//    public ReturnMyProduct getMyProduct(@AuthenticationPrincipal User user){
//        return myProductService.getMyProduct(user);
//    }



}
