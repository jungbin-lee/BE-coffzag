package com.mini.coffzag.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequestDto {

    private String userPhone;
    private String userAddress;
    private Long totalPrice;
    private String payMethod;

}
