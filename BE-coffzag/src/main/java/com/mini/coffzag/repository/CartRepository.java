package com.mini.coffzag.repository;

import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long>{
    List<Cart> findByUser(User user);
}
