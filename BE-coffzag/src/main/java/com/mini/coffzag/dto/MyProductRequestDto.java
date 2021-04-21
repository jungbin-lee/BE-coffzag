package com.mini.coffzag.dto;

import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MyProductRequestDto {
    private User userId;
    private Product coffeeId;




}
