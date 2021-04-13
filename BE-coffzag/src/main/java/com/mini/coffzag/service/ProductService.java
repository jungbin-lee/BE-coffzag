package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    // review
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow(
                () ->  new RuntimeException("coffee_id가 존재하지 않습니다.")
        );
    }
}
