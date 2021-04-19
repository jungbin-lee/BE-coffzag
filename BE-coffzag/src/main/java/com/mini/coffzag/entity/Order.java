package com.mini.coffzag.entity;

import com.mini.coffzag.dto.OrderRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Product coffee;

    @Column(name = "ORDER_CNT", nullable = false)
    private Long orderCnt;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    public Order(Product coffee, Long orderCnt, Cart cart) {
        this.coffee = coffee;
        this.orderCnt = orderCnt;
        this.cart = cart;
    }

    public void update(OrderRequestDto orderRequestDto) {
        this.orderCnt = orderRequestDto.getOrderCnt();
    }

}
