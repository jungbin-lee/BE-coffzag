package com.mini.coffzag.entity;

import com.mini.coffzag.dto.ReviewDto;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Review extends Timestamped{

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "review_id")
    private Long reviewId;

    @Column
    private String username;

    @Column
    private Long coffeeId;

    @Column
    private String contents;

    //테이블 조인을 하자 흑흑
//    @ManyToOne
//    @JoinColumn(name = "COFFEE_ID")
//    private Product product;

    public Review(ReviewDto reviewDto){
        this.coffeeId = reviewDto.getCoffeeId();
        this.username = reviewDto.getUsername();
        this.contents = reviewDto.getContents();
    }

    public void update(ReviewDto reviewDto){
        this.contents = reviewDto.getContents();
    }

}
