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

    // 로그인한 user의 장바구니에서 모든 order 조회
    @Transactional
    public ReturnOrder readOrder(User user) {
        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당하는 장바구니가 없습니다.")
        );

        List<Order> orderList = orderRepository.findByCart(cart);

        ReturnMsg returnMsg = new ReturnMsg("해당 사용자의 장바구니에서 모든 상품을 조회했습니다.");
        ReturnOrder returnOrder = new ReturnOrder(true, orderList, returnMsg);

        return returnOrder;
    }

    // 로그인한 user의 장바구니에 새로운 order 추가
    @Transactional
    public ReturnOrder createOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        Product coffee = productRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 커피 캡슐이 없습니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        List<Order> orderList = orderRepository.findByCart(cart);
        for (Order order : orderList) {
            if (order.getCoffee().getCoffeeId().equals(coffeeId)) {
                OrderRequestDto updateCnt = new OrderRequestDto(orderRequestDto.getOrderCnt() + order.getOrderCnt());
                order.update(updateCnt);

                ReturnMsg returnMsg = new ReturnMsg("[수정 완료] 해당 상품의 개수를 " + updateCnt.getOrderCnt() + "개로 변경하였습니다.");
                ReturnOrder returnOrder = new ReturnOrder(true, order, returnMsg);

                return returnOrder;
            }
        }

        Order newOrder = new Order(coffee, orderRequestDto.getOrderCnt(), cart);
        orderRepository.save(newOrder);

        ReturnMsg returnMsg = new ReturnMsg("해당 상품을 장바구니에 추가했습니다.");
        ReturnOrder returnOrder = new ReturnOrder(true, newOrder, returnMsg);

        return returnOrder;
    }


    // 로그인한 user의 장바구니에서 특정 order(상품)의 orderCnt(수량) 변경
    @Transactional
    public ReturnOrder updateOrder(Long coffeeId, OrderRequestDto orderRequestDto, User user) {
        Product coffee = productRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 커피 캡슐이 없습니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeAndCart(coffee, cart).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        getOrder.update(orderRequestDto);

        ReturnMsg returnMsg = new ReturnMsg("[수정 완료] 해당 상품의 개수를 " + orderRequestDto.getOrderCnt() + "개로 변경하였습니다.");
        ReturnOrder returnOrder = new ReturnOrder(true, getOrder, returnMsg);

        return returnOrder;
    }

    // 로그인한 user의 장바구니에서 특정 order 삭제
    @Transactional
    public ReturnMsg deleteOrder(Long coffeeId, User user) {
        Product coffee = productRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 커피 캡슐이 없습니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new NullPointerException("해당하는 사용자 또는 장바구니가 없습니다.")
        );

        Order getOrder = orderRepository.findByCoffeeAndCart(coffee, cart).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 장바구니에 없습니다.")
        );

        orderRepository.delete(getOrder);

        ReturnMsg returnMsg = new ReturnMsg("[삭제 완료] 장바구니에서 해당 상품을 삭제하였습니다.");

        return returnMsg;
    }

}
