package com.mini.coffzag.response;

import com.mini.coffzag.entity.MyProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnMyProduct {
    private List<MyProduct> myProductList =new ArrayList<>();
    private String msg;
    private boolean ok;


}
