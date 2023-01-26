package com.hkblog.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "hkblog-config")
public class AppConfig {

    public static String JwtKey;
}
