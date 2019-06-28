package com.example.wxgzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan("com.example.wxgzh.**.dao")
@ServletComponentScan
public class WxgzhApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxgzhApplication.class, args);
    }
}
