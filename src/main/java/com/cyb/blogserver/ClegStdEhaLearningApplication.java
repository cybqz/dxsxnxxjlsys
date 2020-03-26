package com.cyb.blogserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.ResourceUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author CYB
 */
@SpringBootApplication
@MapperScan({"com.cyb.blogserver.dao"})
public class ClegStdEhaLearningApplication{

    public static void main(String[] args) {

        SpringApplication.run(ClegStdEhaLearningApplication.class, args);
    }

    /*@Override
     protected void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/**")
                 .addResourceLocations(ResourceUtils.CLASSPATH_URL_PREFIX + "/static/");
         super.addResourceHandlers(registry);
     }*/
}
