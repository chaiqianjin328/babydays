package com.babydays;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan(value="com.babydays.dao")
public class BabydaysApplication {

    public static void main(String[] args) {
        SpringApplication.run(BabydaysApplication.class, args);
    }

}
