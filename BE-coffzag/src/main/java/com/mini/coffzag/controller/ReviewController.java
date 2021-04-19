package com.mini.coffzag.controller;

import com.mini.coffzag.dto.ReviewDto;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;



@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    //상세페이지 (커피 상세 + 리뷰)
    @GetMapping("/api/details/{coffeeId}")
    public ReturnReview getDetailsWithReview(@PathVariable Long coffeeId){
        return reviewService.getDetailsWithReview(coffeeId);
    }

    //리뷰 작성
    @PostMapping("/api/reviews/{coffeeId}")
    public ReturnReview createReview(@PathVariable Long coffeeId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        return reviewService.createReview(user, coffeeId, reviewDto);
    }

    //리뷰 수정
    @PutMapping("/api/reviews/{reviewId}")
    public Review updateReview(@PathVariable Long reviewId, @RequestBody ReviewDto reviewDto, @AuthenticationPrincipal User user){
        return reviewService.updateReview(reviewId, user, reviewDto);
    }

    //리뷰 삭제
    @DeleteMapping("/api/reviews/{reviewId}")
    public ReturnReview deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user){
        return reviewService.deleteReview(reviewId, user);
    }

}
