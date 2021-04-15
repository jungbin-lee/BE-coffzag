package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    //상품상세와 그에 따른 댓글 불러오기
    public ReturnReview getDetailsWithReview(Long coffeeId){
        List<Review> reviewList = reviewRepository.findByCoffeeIdOrderByCreatedAt(coffeeId);

        Product product = productRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("상품 ID가 존재하지 않습니다.")
        );

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        ReturnReview returnReview = new ReturnReview();
        returnReview.setOk(true);
        returnReview.setReviews(reviewList);
        returnReview.setProducts(productList);
        return returnReview;
    }

    public void createReview(String username, Long coffeeId, String contents){ //반환값 있게 할 지?
        Review review = new Review();
        review.setUsername(username);
        review.setCoffeeId(coffeeId);
        review.setContents(contents);
        reviewRepository.save(review);
    }
}
