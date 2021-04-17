package com.mini.coffzag.response;

import com.mini.coffzag.entity.Order;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnOrder {
    private boolean ok;
    private List<Order> orderList = new ArrayList<>();
}
