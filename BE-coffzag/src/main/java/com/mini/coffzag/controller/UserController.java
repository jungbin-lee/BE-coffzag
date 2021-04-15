package com.mini.coffzag.controller;

import com.mini.coffzag.response.ReturnCheckId;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.UserRepository;
import com.mini.coffzag.response.ReturnUser;
import com.mini.coffzag.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserService userService;

    // 회원가입
    @PostMapping("/api/signup")
    public Long join(@RequestBody Map<String, String> user) {
        User newUser = User.builder()
                .username(user.get("username"))
                .password(passwordEncoder.encode(user.get("password")))
                .email(user.get("email")).build();
        userRepository.save(newUser);

        return newUser.getUserId();
    }

    // ID 중복 체크
    @PostMapping("/api/signup/checkid")
    public ReturnCheckId checkId(@RequestBody Map<String, String> user){
        ReturnCheckId returnCheckId = new ReturnCheckId();
        Optional<User> member = userRepository.findByUsername(user.get("username"));
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
    public ReturnUser login(@RequestBody Map<String, String> user) {
        return userService.login(user);
    }
}