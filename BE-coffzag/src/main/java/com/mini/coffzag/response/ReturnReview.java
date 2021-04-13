package com.mini.coffzag.response;

import com.mini.coffzag.entity.Review;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnReview {
    private boolean ok;
    private List<Review> results = new ArrayList<>();
}
