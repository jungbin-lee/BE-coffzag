package com.mini.coffzag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "myproducts")
public class MyProduct {
    @Id
    @Column(name = "MYPRODUCT_ID")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long myProductId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    public MyProduct(Product product, User user){
        this.user = user;
        this.product = product;
    }

}
