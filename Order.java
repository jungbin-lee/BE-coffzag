package com.mini.coffzag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cart_id", nullable = false)
    private Cart cartId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "coffee_id", nullable = false)
    private Product coffeeId;

    @Column(name = "order_cnt", nullable = false)
    private int orderCnt;

}
