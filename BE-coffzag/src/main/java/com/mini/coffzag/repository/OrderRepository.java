package com.mini.coffzag.repository;

import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByCart(Cart cart);
    Optional<Order> findByCoffeeIdAndCart(Long coffeeId, Cart cart);

}
