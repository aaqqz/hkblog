package com.hkblog;

import com.hkblog.api.config.AppConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties(AppConfig.class)
@SpringBootApplication
public class HkblogApplication {

    public static void main(String[] args) {
        SpringApplication.run(HkblogApplication.class, args);
    }

}
