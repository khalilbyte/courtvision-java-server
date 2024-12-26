package com.khalilgayle.courtvisionserver.teams;

import com.khalilgayle.courtvisionserver.errorhandling.PlayersNotFoundException;
import com.khalilgayle.courtvisionserver.errorhandling.TeamsNotFoundException;
import com.khalilgayle.courtvisionserver.players.PlayerSummary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class NbaTeamService {
    private final WebClient webClient;

    public NbaTeamService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Flux<NbaTeam> getTeams() {
        return webClient.get().uri("teams").accept(MediaType.APPLICATION_JSON).exchangeToFlux(clientResponse -> {
            if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                return Flux.error(new TeamsNotFoundException("No teams found"));
            }
            if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                return clientResponse.bodyToFlux(NbaTeam.class);
            } else {
                return Flux.error(new RuntimeException("Internal service error occurred while fetching teams"));
            }
        });
    }

    public Flux<PlayerSummary> getTeamRoster(long teamId) {
        return webClient.get()
                .uri(UriBuilder -> UriBuilder
                        .path("/teams/{teamId}/players")
                        .build(teamId))
                .accept(MediaType.APPLICATION_JSON)
                .exchangeToFlux(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.NOT_FOUND)) {
                        return Flux.error(new PlayersNotFoundException("No team roster found"));
                    } else if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToFlux(PlayerSummary.class);
                    } else {
                        return Flux.error(new RuntimeException("Internal service error occurred while attempting to fetch roster"));
                    }
                });
    }
}
