package com.mini.coffzag.service;

import com.mini.coffzag.dto.OrderRequestDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.Order;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.OrderRepository;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public ReturnOrder readOrder(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당하는 장바구니가 없습니다.")
        );

        ReturnOrder returnOrder = new ReturnOrder();
        List<Order> orderList = orderRepository.findByCart(cart);
        returnOrder.setOrderList(orderList);
        returnOrder.setOk(true);

        return returnOrder;
    }

    @Transactional
    public ReturnOrder createOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order order = new Order(coffeeId, orderRequestDto.getOrderCnt(), cart);
        orderRepository.save(order);

        ReturnOrder returnOrder = new ReturnOrder();
        List<Order> orderList = orderRepository.findByCart(cart);
        returnOrder.setOrderList(orderList);
        returnOrder.setOk(true);

        return returnOrder;
    }

    //장바구니에서 상품 변경
    @Transactional
    public ReturnMsg updateOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeIdAndCart(coffeeId, cart).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        getOrder.update(orderRequestDto);

        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setMsg("[수정 완료] 해당 상품의 개수를 " + orderRequestDto.getOrderCnt() + "개로 변경하였습니다.");

        return returnMsg;
    }

    @Transactional
    public ReturnMsg deleteOrder(Long coffeeId, User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeIdAndCart(coffeeId, cart).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        orderRepository.delete(getOrder);

        ReturnMsg returnMsg = new ReturnMsg();
        returnMsg.setMsg("[삭제 완료] 장바구니에서 해당 상품을 삭제하였습니다.");

        return returnMsg;
    }

}
