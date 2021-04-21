package com.mini.coffzag.response;

import com.mini.coffzag.entity.MyProduct;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnMyProduct {
    private boolean ok;
    private List<MyProduct> myProductList = new ArrayList<>();
    private ReturnMsg returnMsg;

    public ReturnMyProduct(boolean ok, List<MyProduct> myProductList, ReturnMsg returnMsg) {
        this.ok = ok;
        this.myProductList = myProductList;
        this.returnMsg = returnMsg;
    }

    public ReturnMyProduct(boolean ok, ReturnMsg returnMsg) {
        this.ok = ok;
        this.returnMsg = returnMsg;
    }
}
