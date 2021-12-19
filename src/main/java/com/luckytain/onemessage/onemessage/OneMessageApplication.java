package com.luckytain.onemessage.onemessage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luckytain.onemessage.onemessage.Mapper")

public class OneMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(OneMessageApplication.class, args);
    }

}
