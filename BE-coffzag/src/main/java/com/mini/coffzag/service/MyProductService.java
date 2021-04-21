package com.mini.coffzag.service;

import com.mini.coffzag.entity.MyProduct;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.MyProductRepository;
import com.mini.coffzag.repository.ProductRepository;
import com.mini.coffzag.repository.UserRepository;
import com.mini.coffzag.response.ReturnMsg;
import com.mini.coffzag.response.ReturnMyProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MyProductService {

    private final MyProductRepository myProductRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Transactional
    public ReturnMsg updateMyProduct(Long coffeeId, User user) {

        Product product = productRepository.findByCoffeeId(coffeeId).orElseThrow(
                () -> new IllegalArgumentException("해당하는 상품이 존재하지 않습니다.")
        );

        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        Optional<MyProduct> myProductValid = myProductRepository.findByProductAndUser(product, user);
        ReturnMsg returnMsg = new ReturnMsg("");

        if (!myProductValid.isPresent()) {
            MyProduct newMyProduct = new MyProduct(product, user);
            myProductRepository.save(newMyProduct);
            returnMsg.setMsg("찜하기 등록 완료!");
        } else {
            MyProduct myProduct = myProductValid.get();
            myProductRepository.delete(myProduct);
            returnMsg.setMsg("찜하기 취소 완료!");
        }

        return returnMsg;
    }

    @Transactional
    public ReturnMyProduct allMyProducts(User user) {
        userRepository.findByUserId(user.getUserId()).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 사용자입니다.")
        );

        List<MyProduct> myProductList = myProductRepository.findByUser(user);
        ReturnMsg returnMsg = new ReturnMsg("");

        if (!myProductList.isEmpty()) {
            returnMsg.setMsg("해당 사용자가 찜한 상품을 모두 조회하였습니다.");
            ReturnMyProduct returnMyProduct = new ReturnMyProduct(true, myProductList, returnMsg);
            return returnMyProduct;
        } else {
            returnMsg.setMsg("해당 사용자가 찜한 상품이 없습니다.");
            ReturnMyProduct returnMyProduct = new ReturnMyProduct(true, returnMsg);
            return returnMyProduct;
        }
    }

}
