package com.mini.coffzag.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReturnUser {
    private String token;
    private String username;
    private String msg;
}
