package com.khalilgayle.courtvisionserver;

import com.khalilgayle.courtvisionserver.players.PlayerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@EnableCaching
@SpringBootApplication
public class CourtvisionServerApplication implements CommandLineRunner {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final String BASE_URL = "http://localhost:8000";

    public static void main(String[] args) {
        SpringApplication.run(CourtvisionServerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("The server has started running...");
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl(BASE_URL).build();
    }
}
