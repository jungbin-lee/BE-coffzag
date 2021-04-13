package com.mini.coffzag.service;

import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public ReturnReview readReview(Long productId){
        List<Review> reviewList = reviewRepository.findByProductIdOrderByCreatedAt(productId);

        ReturnReview returnReview = new ReturnReview();
        returnReview.setOk(true);
        returnReview.setResults(reviewList);
        return returnReview;
    }

    public void createReview(Long productId, String contents){ //반환값 있게 할 지?
        Review review = new Review();
        review.setProductId(productId);
        review.setContents(contents);
        reviewRepository.save(review);
    }
}
