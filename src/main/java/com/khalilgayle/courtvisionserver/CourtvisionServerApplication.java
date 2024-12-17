package com.khalilgayle.courtvisionserver;

import com.khalilgayle.courtvisionserver.players.PlayerListDTO;
import com.khalilgayle.courtvisionserver.players.PlayerListDTOResponse;
import com.khalilgayle.courtvisionserver.players.PlayerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class CourtvisionServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CourtvisionServerApplication.class, args);
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder.baseUrl("http://127.0.0.1:8000").build();
    }

    @Bean
    CommandLineRunner commandLineRunner(PlayerService playerService) {
        return args -> {
            playerService.getPlayers(1, 5)
                    .map(PlayerListDTOResponse::getPlayers)
                    .flatMapMany(Flux::fromIterable)
                    .subscribe(System.out::println);
        };
    }
}
