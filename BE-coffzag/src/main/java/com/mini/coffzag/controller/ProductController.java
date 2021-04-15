package com.mini.coffzag.controller;

import com.mini.coffzag.response.ReturnProduct;
import com.mini.coffzag.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    //메인페이지 모든 커피
    @GetMapping("/api/products")
    public ReturnProduct getProducts() {
        return productService.getProducts();
    }

    //브랜드별 커피
    @GetMapping("/api/products/{coffeeBrand}")
    public ReturnProduct getBrand(@PathVariable String coffeeBrand){
        return productService.getBrand(coffeeBrand);
    }

    //가격 낮은 순
    @GetMapping("api/products/price")
    public ReturnProduct getProductsByPriceAsc(){
        return productService.getProductsByPriceAsc();
    }


}
