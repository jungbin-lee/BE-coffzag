package com.mini.coffzag.entity;

import com.mini.coffzag.dto.CartRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="cart")
public class Cart extends Timestamped {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Long cartid;

    @ManyToOne
    @JoinColumn(name ="product_productid")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_userid")
    private User user;

    @Column(nullable = false)
    private int count;

    public Cart(Product product, User user, CartRequestDto cartRequestDto) {

        this.product = product;
        this.user = user;
        this.count = cartRequestDto.getCount();
    }

    public void update(CartRequestDto cartRequestDto){
        this.count = cartRequestDto.getCount();
    }
}