package com.mini.coffzag.controller;

import com.mini.coffzag.entity.User;
import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.security.JwtTokenProvider;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;
    private final JwtTokenProvider jwtTokenProvider;

//    @GetMapping("/api/reviews/{productId}")
    @GetMapping("/api/details/{coffeeId}")
    public ReturnReview getDetailsWithReview(@PathVariable Long coffeeId, @AuthenticationPrincipal User user){
        System.out.println(user.getUsername());
        return reviewService.getDetailsWithReview(coffeeId);
    }

    @PostMapping("/api/reviews/{coffeeId}") //댓글 단 사람 필요
    public void createReview(@PathVariable Long coffeeId, @RequestBody Map<String, String> requestData, @AuthenticationPrincipal User user){
        //System.out.println(user.getUsername());
        String contents = requestData.get("contents");
        reviewService.createReview(coffeeId, contents);
    }

}
