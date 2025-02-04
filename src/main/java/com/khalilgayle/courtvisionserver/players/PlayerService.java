package com.khalilgayle.courtvisionserver.players;

import com.khalilgayle.courtvisionserver.errorhandling.InvalidQueryParametersException;
import com.khalilgayle.courtvisionserver.errorhandling.PlayerNotFoundException;
import com.khalilgayle.courtvisionserver.errorhandling.PlayersNotFoundException;
import com.khalilgayle.courtvisionserver.errorhandling.StatsNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PlayerService {
    private final WebClient webClient;


    public PlayerService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<PlayerAverages> getCareerAverages(long playerId) {
        return webClient
                .get()
                .uri(UriBuilder -> UriBuilder
                        .path("/players/{playerId}/career-averages")
                        .build(playerId))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Flux.error(new StatsNotFoundException("Unable to fetch player stats at this time"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToFlux(PlayerAverages.class);
                    } else {
                        return Flux.error(new RuntimeException("Internal service error occurred while attempting to fetch stats"));
                    }
                });
    }

    public Flux<PlayerSummary> getPlayersBySearch(String keyword) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players/search")
                        .queryParam("keyword", keyword)
                        .build()
                )
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Flux.error(new PlayersNotFoundException("No players found"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.BAD_REQUEST)) {
                        return Flux.error(new InvalidQueryParametersException("Value must be minimum 3 characters"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToFlux(PlayerSummary.class);
                    } else {
                        return Flux.error(new RuntimeException("Internal service error occurred while fetching players"));
                    }
                });
    }

    public Mono<PlayerSummary> getPlayerById(long playerId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players")
                        .queryParam("player_id", playerId)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new PlayerNotFoundException("No player found"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(PlayerSummary.class);
                    } else {
                        return Mono.error(new RuntimeException("Internal service error occurred while fetching player with ID: " + playerId));
                    }
                });
    }

    public Mono<PlayerSummaryResponse> getPlayers(int page, int playersPerPage) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/players")
                        .queryParam("page", page)
                        .queryParam("players_per_page", playersPerPage)
                        .build()
                )
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Mono.error(new PlayerNotFoundException("No players found"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(PlayerSummaryResponse.class);
                    } else {
                        return Mono.error(new RuntimeException("Internal service error occurred while fetching players"));
                    }
                });
    }

    public Flux<PlayerCategoryLeader> getCategoryLeaders(int numberOfPlayers, String category) {
        return webClient.get()
                .uri(UriBuilder ->
                        UriBuilder.path("/players/categories")
                                .queryParam("number_of_players", numberOfPlayers)
                                .queryParam("category", category)
                                .build()
                )
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Flux.error(new PlayersNotFoundException("No players found"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToFlux(PlayerCategoryLeader.class);
                    } else {
                        return Flux.error(new RuntimeException("Internal service error occurred while fetching players"));
                    }
                });
    }
}

