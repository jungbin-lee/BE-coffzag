package com.mini.coffzag.service;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ProductRepository productRepository;

    public ReturnReview readReview(Long productId){
        List<Review> reviewList = reviewRepository.findByProductIdOrderByCreatedAt(productId);
//        Product product = productRepository.findById(productId).orElseThrow(
//                () -> new IllegalArgumentException()
//        );
        List<Product> productList = productRepository.findByCoffeeId(productId);

        ReturnReview returnReview = new ReturnReview();
        returnReview.setOk(true);
        returnReview.setReviews(reviewList);
        returnReview.setProducts(productList);
        return returnReview;
    }

    public void createReview(Long productId, String contents){ //반환값 있게 할 지?
        Review review = new Review();
        review.setProductId(productId);
        review.setContents(contents);
        reviewRepository.save(review);
    }
}
