package com.mini.coffzag.controller;

import com.mini.coffzag.dto.LoginDto;
import com.mini.coffzag.dto.UserDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.response.ReturnCheckId;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.UserRepository;
import com.mini.coffzag.response.ReturnUser;
import com.mini.coffzag.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final CartRepository cartRepository;

    @PostMapping("/api/signup")
    public void createUser(@RequestBody UserDto userDto) {
        User user = new User(userDto);
        userRepository.save(user);

        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }

    // ID 중복 체크
    @PostMapping("/api/signup/checkid")
    public ReturnCheckId checkId(@RequestBody UserDto userDto){
        ReturnCheckId returnCheckId = new ReturnCheckId();
        Optional<User> member = userRepository.findByUsername(userDto.getUsername());
        if(member.isPresent()){
            returnCheckId.setOk(false);
            returnCheckId.setMsg("중복된 ID가 존재합니다.");
        } else{
            returnCheckId.setOk(true);
            returnCheckId.setMsg("사용 가능한 ID 입니다.");
        }
        return returnCheckId;
    }

    // 로그인
    @PostMapping("/api/login")
    public ReturnUser login(@RequestBody LoginDto loginDto) {
        return userService.login(loginDto);
    }
}