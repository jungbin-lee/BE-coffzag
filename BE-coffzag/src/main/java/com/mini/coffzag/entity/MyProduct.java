package com.mini.coffzag.entity;

import com.mini.coffzag.dto.MyProductRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table (name = "myproducts")
public class MyProduct {
    @Id
    @Column(name = "myproduct_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long myproductId;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Product coffeeId;



    public MyProduct(Product coffeeId, User user, MyProductRequestDto productDto){

        this.user = user;
        this.coffeeId =coffeeId;


    }



}
