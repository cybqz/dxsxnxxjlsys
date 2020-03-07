package com.cyb.blogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CYB
 */
@SpringBootApplication
@MapperScan({"com.cyb.blogserver.dao"})
public class ClegStdEhaLearningServerApplication {

    public static void main(String[] args) {

        SpringApplication.run(ClegStdEhaLearningServerApplication.class, args);
    }
}
