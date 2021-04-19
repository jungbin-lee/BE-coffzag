package com.mini.coffzag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReviewDto {
    private String username;
    private String contents;
    private Long coffeeId;
}
