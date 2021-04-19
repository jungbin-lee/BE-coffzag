package com.mini.coffzag.service;

import com.mini.coffzag.dto.LoginDto;
import com.mini.coffzag.dto.UserDto;
import com.mini.coffzag.entity.Cart;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.CartRepository;
import com.mini.coffzag.repository.UserRepository;
import com.mini.coffzag.response.ReturnCheckId;
import com.mini.coffzag.response.ReturnUser;
import com.mini.coffzag.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final CartRepository cartRepository;


    public void createUser(UserDto userDto){
        User user = new User(userDto);
        userRepository.save(user);

        Cart cart = new Cart(user);
        cartRepository.save(cart);
    }


    public ReturnCheckId checkId(UserDto userDto){
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


    public ReturnUser login(LoginDto loginDto) {
        ReturnUser returnUser = new ReturnUser();
        try {
            User member = userRepository.findByUsername(loginDto.getUsername())
                    .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 ID 입니다."));
            if (!passwordEncoder.matches(loginDto.getPassword(), member.getPassword())) {
                throw new IllegalArgumentException("비밀번호를 다시 확인해 주세요.");
            }
            returnUser.setToken(jwtTokenProvider.createToken(member.getUsername()));
            returnUser.setUsername(member.getUsername());
            return returnUser;
        } catch (IllegalArgumentException e) {
            returnUser.setMsg(e.getMessage());
            return returnUser;
        }
    }
}
