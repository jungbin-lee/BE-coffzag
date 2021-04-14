package com.mini.coffzag.response;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnReview {
    private boolean ok;
//    private List<Review> results = new ArrayList<>();
    private List<Review> reviews = new ArrayList<>();
    private List<Product> products = new ArrayList<>();
//    private Product products = new Product();
}
