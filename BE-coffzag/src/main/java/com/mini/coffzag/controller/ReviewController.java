package com.mini.coffzag.controller;

import com.mini.coffzag.dto.ReviewDto;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.security.JwtTokenProvider;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;


@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //상세페이지 (커피 상세 + 댓글)
    @GetMapping("/api/details/{coffeeId}")
    public ReturnReview getDetailsWithReview(@PathVariable Long coffeeId){
        return reviewService.getDetailsWithReview(coffeeId);
    }

    @PostMapping("/api/reviews/{coffeeId}")
    public void createReview(@PathVariable Long coffeeId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        String username = user.getUsername();
        reviewDto.setUsername(username);
        reviewDto.setCoffeeId(coffeeId);
        reviewService.createReview(reviewDto);
    }

    @PutMapping("/api/reviews/{reviewId}")
    public void updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        Review review = reviewRepository.findByReviewId(reviewId);
        if (!review.getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }
        reviewService.updateReview(review, reviewDto);
    }

    @DeleteMapping("/api/reviews/{reviewId}")
    public void deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user){
        Review review = reviewRepository.findByReviewId(reviewId);
        if (!review.getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다");
        }
        reviewService.deleteReview(reviewId);

    }

}
