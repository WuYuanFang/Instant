package com.kevin.instant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@MapperScan("com.kevin.instant.mapper")
@EnableScheduling
public class InstantApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstantApplication.class, args);
    }

}
