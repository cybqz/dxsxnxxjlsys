package com.cyb.cleg;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author CYB
 */
@SpringBootApplication(scanBasePackages = {"com.cyb.*"})
@MapperScan({"com.cyb.cleg.dao"})
public class ClegStdEhaLearningApplication{

    public static void main(String[] args) {

        SpringApplication.run(ClegStdEhaLearningApplication.class, args);
    }
}
