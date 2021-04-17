package com.mini.coffzag.service;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Order;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;

    @Transactional
    public List<Order> readOrder(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당하는 장바구니가 없습니다.")
        );

        return orderRepository.findByCart(cart);
    }

    @Transactional
    public Order createOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order order = new Order(coffeeId, orderRequestDto.getOrderCnt(), cart);
        orderRepository.save(order);
        return order;
    }

    @Transactional
    public Order updateOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        getOrder.update(orderRequestDto);
        return getOrder;
    }

    @Transactional
    public void deleteOrder(Long coffeeId, User user) {
        cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        orderRepository.delete(getOrder);
    }

}
