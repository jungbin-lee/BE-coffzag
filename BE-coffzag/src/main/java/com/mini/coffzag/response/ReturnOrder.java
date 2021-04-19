package com.mini.coffzag.response;

import com.mini.coffzag.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ReturnOrder {
    private boolean ok;
    private List<Order> orderList = new ArrayList<>();
    private Order order = new Order();
    private ReturnMsg returnMsg;

    public ReturnOrder(boolean ok, List<Order> orderList, ReturnMsg returnMsg) {
        this.ok = ok;
        this.orderList = orderList;
        this.returnMsg = returnMsg;
    }

    public ReturnOrder(boolean ok, Order order, ReturnMsg returnMsg) {
        this.ok = ok;
        this.order = order;
        this.returnMsg = returnMsg;
    }
}
