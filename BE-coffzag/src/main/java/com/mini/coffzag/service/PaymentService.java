package com.mini.coffzag.service;

import com.mini.coffzag.dto.PaymentRequestDto;
import com.mini.coffzag.entity.*;
import com.mini.coffzag.repository.*;
import com.mini.coffzag.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final OrderRepository orderRepository;
    private final OrderHistoryRepository orderHistoryRepository;
    private final PaymentRepository paymentRepository;

    // 결제 페이지에 user 정보 전달
    @Transactional
    public ReturnUserInfo getUserInfo(User user) {
        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        ReturnUserInfo returnUserInfo = new ReturnUserInfo(true, user.getUsername(), user.getEmail());
        return returnUserInfo;
    }

    // 결제 페이지에서 [결제하기] 버튼 클릭 시, new payment 생성
    @Transactional
    public ReturnMsg createPayment(PaymentRequestDto paymentRequestDto, User user) {
        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 장바구니가 존재하지 않습니다.")
        );

        List<Order> orderList = orderRepository.findByCart(cart);
        List<OrderHistory> orderHistories = new ArrayList<>();
        for (Order order : orderList) {
            OrderHistory orderHistory = new OrderHistory(order.getCart(), order.getCoffee(), order.getOrderCnt());
            orderHistories.add(orderHistory);
            orderHistoryRepository.save(orderHistory);
        }

        Payment newPayment = new Payment(cart, orderHistories, paymentRequestDto);
        paymentRepository.save(newPayment);

        ReturnMsg returnMsg = new ReturnMsg("결제 내역을 성공적으로 저장하였습니다.");
        deleteOrderList(user);
        return returnMsg;
    }

    // 결제 내역 저장 후, 해당 user의 장바구니 비우기
    @Transactional
    public ReturnMsg deleteOrderList(User user) {
        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 장바구니가 존재하지 않습니다.")
        );

        List<Order> orderList = orderRepository.findByCart(cart);
        for (Order order : orderList) {
            orderRepository.delete(order);
        }

        ReturnMsg returnMsg = new ReturnMsg("해당 사용자의 장바구니를 비웠습니다.");
        return returnMsg;
    }

    @Transactional
    public ReturnPayment getPaymentsList(User user) {
        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        Cart cart = cartRepository.findByUser(user).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 장바구니가 존재하지 않습니다.")
        );

        List<Payment> payments = paymentRepository.findByCart(cart);


        ReturnMsg returnMsg = new ReturnMsg("해당 사용자의 payment 내역을 모두 조회했습니다.");

        ReturnPayment returnPayment = new ReturnPayment(true, payments, returnMsg);
        return returnPayment;
    }



}
