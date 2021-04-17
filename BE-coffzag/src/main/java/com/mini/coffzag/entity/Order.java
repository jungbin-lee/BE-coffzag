package com.mini.coffzag.entity;

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

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "coffee_id", nullable = false)
    @Column(name = "COFFEE_ID", nullable = false)
    private Long coffeeId;

    @Column(name = "ORDER_CNT", nullable = false)
    private Long orderCnt;

    @ManyToOne
    @JoinColumn(name = "CART_ID")
    private Cart cartId;

    public Order(Long coffeeId, Long orderCnt) {
        this.coffeeId = coffeeId;
        this.orderCnt = orderCnt;
    }

    public void update(Long coffeeId, Long orderCnt) {
        this.coffeeId = coffeeId;
        this.orderCnt = orderCnt;
    }

}
