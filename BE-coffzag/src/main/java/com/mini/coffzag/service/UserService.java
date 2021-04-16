package com.mini.coffzag.service;

import com.mini.coffzag.dto.LoginDto;
import com.mini.coffzag.entity.User;
import com.mini.coffzag.repository.UserRepository;
import com.mini.coffzag.response.ReturnUser;
import com.mini.coffzag.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

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
