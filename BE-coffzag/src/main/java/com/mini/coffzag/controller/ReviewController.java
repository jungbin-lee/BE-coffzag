package com.mini.coffzag.controller;

import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

//    @GetMapping("/api/reviews/{productId}")
    @GetMapping("/api/details/{coffeeId}")
    public ReturnReview getDetailsWithReview(@PathVariable Long coffeeId){
        return reviewService.getDetailsWithReview(coffeeId);
    }

    @PostMapping("/api/reviews/{coffeeId}") //댓글 단 사람 필요
    public void createReview(@PathVariable Long coffeeId, @RequestBody Map<String, String> requestData, @AuthenticationPrincipal User user){
//        System.out.println(user);
        String contents = requestData.get("contents");
        reviewService.createReview(coffeeId, contents);
    }

}
