package com.mini.coffzag.service;

import com.mini.coffzag.dto.ReviewDto;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.ReviewRepository;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnReview;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


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

    //리뷰 등록
    public ReturnMsg createReview(ReviewDto reviewDto){
        Review review = new Review(reviewDto);
        reviewRepository.save(review);
        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setMsg("리뷰 등록 완료");
        return returnMsg;
    }

    //리뷰 수정
    @Transactional
    public ReturnMsg updateReview(Review review, ReviewDto reviewDto){
        review.update(reviewDto);
        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setMsg("리뷰 수정 완료");
        return returnMsg;
    }

    //리뷰 삭제
    public ReturnMsg deleteReview(Long reviewId){
        reviewRepository.deleteById(reviewId);
        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setMsg("리뷰 삭제 완료");
        return returnMsg;
    }

}
