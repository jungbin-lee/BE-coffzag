package com.mini.coffzag.response;

import com.mini.coffzag.entity.OrderHistory;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReturnOrderHistory {
    private List<OrderHistory> orderHistories;
}
