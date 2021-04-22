package com.mini.coffzag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "orderhistory")
public class OrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderHistoryId;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    @JsonIgnore
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "COFFEE_ID")
    private Product product;

    @Column(name = "ORDER_CNT", nullable = false)
    private Long orderCnt;

    public OrderHistory(Cart cart, Product product, Long orderCnt) {
        this.cart = cart;
        this.product = product;
        this.orderCnt = orderCnt;

    }
}
