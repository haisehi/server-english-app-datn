package com.example.english_learning_server.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class configCloudinary {
    @Bean
    public Cloudinary configKey(){
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dbxau223u");
        config.put("api_key", "723961168917624");
        config.put("api_secret", "mtw68Ar2pw7wivN9fpbBsj3FpVk");
        return new Cloudinary(config);
    }
}
