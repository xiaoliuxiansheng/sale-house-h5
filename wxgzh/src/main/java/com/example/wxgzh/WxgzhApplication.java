package com.example.wxgzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@MapperScan("com.example.wxgzh.**.dao")
@ServletComponentScan
@EnableTransactionManagement
public class WxgzhApplication {
    public static void main(String[] args) {
        SpringApplication.run(WxgzhApplication.class, args);
    }
}
