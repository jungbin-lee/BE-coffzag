package com.mini.coffzag.controller;

import com.mini.coffzag.response.ReturnReview;
import com.mini.coffzag.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

//    @GetMapping("/api/reviews/{productId}")
    @GetMapping("/api/details/{productId}")
    public ReturnReview readReview(@PathVariable Long productId){
        return reviewService.readReview(productId);
    }

    @PostMapping("/api/details/{productId}") //댓글 단 사람 필요
    public void createReview(@PathVariable Long productId, @RequestBody Map<String, String> requestData){
        String contents = requestData.get("contents");
        reviewService.createReview(productId, contents);
    }

}
