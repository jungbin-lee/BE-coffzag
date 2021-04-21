package com.mini.coffzag.service;

import com.mini.coffzag.dto.MyProductRequestDto;
import com.mini.coffzag.entity.MyProduct;
import com.mini.coffzag.entity.Product;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.MyProductRepository;
import com.mini.coffzag.response.ReturnMyProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MyProductService {
    private final MyProductRepository myProductRepository;


    public ReturnMyProduct addMyProduct(Product coffeeId , User user, MyProductRequestDto productDto){


        MyProduct myProduct = new MyProduct(coffeeId,user,productDto);
        myProductRepository.save(myProduct);


        ReturnMyProduct returnMsg = new ReturnMyProduct();
        returnMsg.setOk(true);
        returnMsg.setMsg("찜하기 완료");
        return returnMsg;


    }




//    public ReturnMyProduct getMyProduct(User user){
//        List<MyProduct> myProductList =myProductRepository.findByUserId(user.getUserId());
//        ReturnMyProduct returnMyProduct = new ReturnMyProduct();
//        returnMyProduct.setMyProductList(myProductList);
//        returnMyProduct.setOk(true);
//
//        return returnMyProduct;
//    }



}
