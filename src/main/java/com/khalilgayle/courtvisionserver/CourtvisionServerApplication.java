package com.khalilgayle.courtvisionserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class CourtvisionServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourtvisionServerApplication.class, args);
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://127.0.0.1:8000").build();
    }
}
