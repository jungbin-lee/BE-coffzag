package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    //메인 페이지 모든 커피
    public ReturnProduct getProducts() {
        List<Product> productList = productRepository.findAll();
        List<Review> reviewList = new ArrayList<>();

        for (Product product : productList){
            Long coffeeId = product.getCoffeeId();
            Review review = reviewRepository.findFirstByCoffeeIdOrderByModifiedAtDesc(coffeeId);
            //coffeeId로 리뷰들을 찾고 생성시간이 가장 최근인 것
            reviewList.add(review);
        }

        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setOk(true);
        returnProduct.setProducts(productList);
        returnProduct.setReviews(reviewList);
        return returnProduct;
    }

    //브랜드 별 커피
    public ReturnProduct getBrand(String coffeeBrand){
        List<Product> productList = productRepository.findByCoffeeBrand(coffeeBrand);
        List<Review> reviewList = new ArrayList<>();

        for (Product product : productList){
            Long coffeeId = product.getCoffeeId();
            Review review = reviewRepository.findFirstByCoffeeIdOrderByModifiedAtDesc(coffeeId);
            //coffeeId로 리뷰들을 찾고 생성시간이 가장 최근인 것
            reviewList.add(review);
        }

        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setOk(true);
        returnProduct.setProducts(productList);
        returnProduct.setReviews(reviewList);
        return returnProduct;
    }

    //가격 낮은 순
    public ReturnProduct getProductsByPriceAsc(){
        List<Product> productList = productRepository.findAllByOrderByCoffeePriceAsc();
        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setOk(true);
        returnProduct.setProducts(productList);
        return returnProduct;
    }
}
