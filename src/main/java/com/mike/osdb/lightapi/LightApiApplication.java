package com.mike.osdb.lightapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.mike.osdb.lightapi.dao"})
@PropertySource("classpath:application.properties")
@SpringBootApplication
public class LightApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(LightApiApplication.class, args);
    }
}
