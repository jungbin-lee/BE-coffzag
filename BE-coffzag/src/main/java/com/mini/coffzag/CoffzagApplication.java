package com.mini.coffzag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class CoffzagApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoffzagApplication.class, args);
    }

}
