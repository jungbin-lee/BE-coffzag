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
    @JsonIgnore
    private Cart cart;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USERNAME")
    private User user;

    @Column(name = "USER_PHONE")
    private String userPhone;               //PaymentRequestDto 로 받을 정보

    @Column(name = "USER_ADDRESS")
    private String userAddress;             //PaymentRequestDto 로 받을 정보


    //@OneToMany(fetch = FetchType.EAGER)
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "PRODUCT_INFO")
    private List<Long> productInfo;
    //private List<Product> coffeeList;


    @Column(name = "TOTAL_PRICE")
    private Long totalPrice;                //PaymentRequestDto 로 받을 정보

    @Column(name = "PAY_METHOD")
    private String payMethod;               //PaymentRequestDto 로 받을 정보

    public Payment(Cart cart, User user, List<Long> productInfo, PaymentRequestDto paymentRequestDto) {
        this.cart = cart;
        this.user = user;
        this.productInfo = productInfo;
        //this.coffeeList = coffeeList;
        this.userPhone = paymentRequestDto.getUserPhone();
        this.userAddress = paymentRequestDto.getUserAddress();
        this.totalPrice = paymentRequestDto.getTotalPrice();
        this.payMethod = paymentRequestDto.getPayMethod();
    }
}
