package com.mini.coffzag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderRequestDto {

    private Long orderCnt;

    public OrderRequestDto(Long orderCnt) {
        this.orderCnt = orderCnt;
    }
}