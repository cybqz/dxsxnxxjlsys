package com.cyb.cleg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CYB
 */
@SpringBootApplication
@MapperScan({"com.cyb.blogserver.dao"})
public class ClegStdEhaLearningApplication{

    public static void main(String[] args) {

        SpringApplication.run(ClegStdEhaLearningApplication.class, args);
    }
}
