package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ReviewRepository reviewRepository;

    //메인 페이지 모든 커피, 페이징 및 정렬
    public ReturnProduct getProducts(int page, int size) {

        // Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
        // Sort sort = Sort.by(direction, sortBy);
        Pageable pageable = PageRequest.of(page, size);
        // pageable : 페이징 하는 방법을 기술해놓은 클래스, page : 실제로 페이징으로 잘려진 객체들을 담고 있는 객체


        Page<Product> productList = productRepository.findAll(pageable);
        List<Review> reviewList = new ArrayList<>();

        for (Product product : productList){
            Long coffeeId = product.getCoffeeId();
            Review review = reviewRepository.findFirstByCoffeeIdOrderByModifiedAtDesc(coffeeId);
            //coffeeId로 리뷰들을 찾고 생성시간이 가장 최근인 것
            reviewList.add(review);
        }

        ReturnProduct returnProduct = new ReturnProduct();
        returnProduct.setOk(true);
        returnProduct.setProduct(productList); //페이징 시 알아서 contents안에 담겨 오기 때문에 따로 리스트 안에 담을 필요 없음
        returnProduct.setReviews(reviewList);
        return returnProduct;
    }

    //메인 페이지 모든 커피
//    public ReturnProduct getProducts() {
//        List<Product> productList = productRepository.findAll();
//        List<Review> reviewList = new ArrayList<>();
//
//        for (Product product : productList){
//            Long coffeeId = product.getCoffeeId();
//            Review review = reviewRepository.findFirstByCoffeeIdOrderByModifiedAtDesc(coffeeId);
//            //coffeeId로 리뷰들을 찾고 생성시간이 가장 최근인 것
//            reviewList.add(review);
//        }
//
//        ReturnProduct returnProduct = new ReturnProduct();
//        returnProduct.setOk(true);
//        returnProduct.setProducts(productList);
//        returnProduct.setReviews(reviewList);
//        return returnProduct;
//    }


    //브랜드 별 커피
    public ReturnProduct getBrand(String coffeeBrand){
        List<Product> productList = productRepository.findByCoffeeBrand(coffeeBrand);
        List<Review> reviewList = new ArrayList<>();

        for (Product product : productList){
            Long coffeeId = product.getCoffeeId();
            Review review = reviewRepository.findFirstByCoffeeIdOrderByModifiedAtDesc(coffeeId);
            //coffeeId로 리뷰들을 찾고 수정시간이 가장 최근인 것
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
