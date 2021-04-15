package com.mini.coffzag.controller;

import com.mini.coffzag.response.ReturnProduct;
import com.mini.coffzag.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/api/products")
    public ReturnProduct getProducts() {
        return productService.getProducts();
    }

}
