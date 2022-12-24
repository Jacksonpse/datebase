package com.example.safefdu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication()
@MapperScan("com.example.safefdu.dao")
public class SafefduApplication {

    public static void main(String[] args) {
        SpringApplication.run(SafefduApplication.class, args);
    }

}
