package com.mini.coffzag.repository;

import com.mini.coffzag.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAll();
    Page<Product> findAll(Pageable pageable); //페이징은 list 대신에 page쓴다
    Optional<Product> findByCoffeeId(Long coffeeId);
    List<Product> findByCoffeeBrand(String brand);
    List<Product> findAllByOrderByCoffeePriceAsc();
}
