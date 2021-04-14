package com.mini.coffzag.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {

    @Id
    @Column(name = "coffee_id")
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long coffeeId;

    @Column(name = "coffee_img", length = 255, nullable = false)
    private String coffeeImg;

    @Column(name = "coffee_name", length = 255, nullable = false)
    private String coffeeName;

    @Column(name = "coffee_unit", length = 255, nullable = false)
    private String coffeeUnit;

    @Column(name = "coffee_price", nullable = false)
    private int coffeePrice;

    @Column(name = "coffee_info", length = 255, nullable = false)
    private String coffeeInfo;

    @Column(name = "coffee_brand", length = 255, nullable = false)
    private String coffeeBrand;


}
