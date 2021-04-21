package com.mini.coffzag.repository;

import com.mini.coffzag.entity.Payment;
import com.mini.coffzag.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUser(User user);
}
