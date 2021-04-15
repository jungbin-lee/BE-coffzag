package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.response.ReturnProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ReturnProduct getProducts() {
        List<Product> productList = productRepository.findAll();

        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setOk(true);
        returnProduct.setProducts(productList);
        return returnProduct;
    }
}
