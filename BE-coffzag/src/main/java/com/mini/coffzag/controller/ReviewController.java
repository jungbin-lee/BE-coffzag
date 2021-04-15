package com.mini.coffzag.controller;

import com.mini.coffzag.dto.ReviewDto;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.security.JwtTokenProvider;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    //상세페이지 (커피 상세 + 리뷰)
    @GetMapping("/api/details/{coffeeId}")
    public ReturnReview getDetailsWithReview(@PathVariable Long coffeeId){
        return reviewService.getDetailsWithReview(coffeeId);
    }

    //리뷰 작성
    @PostMapping("/api/reviews/{coffeeId}")
    public ReturnMsg createReview(@PathVariable Long coffeeId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        String username = user.getUsername();
        reviewDto.setUsername(username);
        reviewDto.setCoffeeId(coffeeId);
        return reviewService.createReview(reviewDto);
    }

    //리뷰 수정
    @PutMapping("/api/reviews/{reviewId}")
    public ReturnMsg updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        Review review = reviewRepository.findByReviewId(reviewId);
        if (!review.getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("작성자만 수정할 수 있습니다");
        }
        return reviewService.updateReview(review, reviewDto);
    }

    //리뷰 삭제
    @DeleteMapping("/api/reviews/{reviewId}")
    public ReturnMsg deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user){
        Review review = reviewRepository.findByReviewId(reviewId);
        if (!review.getUsername().equals(user.getUsername())){
            throw new IllegalArgumentException("작성자만 삭제할 수 있습니다");
        }
        return reviewService.deleteReview(reviewId);
    }

}
