package com.mini.coffzag.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mini.coffzag.dto.PaymentRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long paymentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CART_ID")
    private Cart cart;

    @Column(name = "USER_PHONE")
    private String userPhone;               //PaymentRequestDto 로 받을 정보

    @Column(name = "USER_ADDRESS")
    private String userAddress;             //PaymentRequestDto 로 받을 정보

    @OneToMany
    @JoinColumn
    private List<OrderHistory> orderHistories;

    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;                //PaymentRequestDto 로 받을 정보

    @Column(name = "PAY_METHOD")
    private String payMethod;               //PaymentRequestDto 로 받을 정보

    public Payment(Cart cart, List<OrderHistory> orderHistories, PaymentRequestDto paymentRequestDto) {
        this.cart = cart;
        this.orderHistories = orderHistories;
        this.userPhone = paymentRequestDto.getUserPhone();
        this.userAddress = paymentRequestDto.getUserAddress();
        this.totalPrice = paymentRequestDto.getTotalPrice();
        this.payMethod = paymentRequestDto.getPayMethod();
    }
}
