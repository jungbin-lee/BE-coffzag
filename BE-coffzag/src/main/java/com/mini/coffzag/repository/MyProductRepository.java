package com.mini.coffzag.repository;

import com.mini.coffzag.entity.MyProduct;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface MyProductRepository extends JpaRepository<MyProduct, Long> {
//    List<MyProduct> findByUserId(Long userId);
//    Optional<MyProduct> findByCoffeeId(Long coffeeid);




}
