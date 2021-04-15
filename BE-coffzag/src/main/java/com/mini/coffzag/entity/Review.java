package com.mini.coffzag.entity;

import com.mini.coffzag.dto.ReviewDto;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
public class Review {

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

    @CreatedDate
    private LocalDateTime createdAt;

//    @LastModifiedDate
//    private LocalDateTime modifiedAt;

    public Review(ReviewDto reviewDto){
        this.coffeeId = reviewDto.getCoffeeId();
        this.username = reviewDto.getUsername();
        this.contents = reviewDto.getContents();
    }

}
