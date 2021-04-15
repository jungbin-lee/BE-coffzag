package com.mini.coffzag.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReviewDto {
    private String username;
    private String contents;
    private Long coffeeId;
}
