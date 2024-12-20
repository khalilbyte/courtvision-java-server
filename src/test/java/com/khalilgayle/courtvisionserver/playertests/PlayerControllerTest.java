package com.khalilgayle.courtvisionserver.playertests;

import com.khalilgayle.courtvisionserver.players.PlayerSummary;
import com.khalilgayle.courtvisionserver.players.PlayerSummaryResponse;
import com.khalilgayle.courtvisionserver.players.PlayerService;
import com.khalilgayle.courtvisionserver.players.playerexceptions.PlayerNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

// Don't want to actually call the NBA API, just want to mock the results
@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {
    private PlayerService playerService;

    @Mock
    WebClient webClient;

    @Mock
    WebClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    WebClient.RequestHeadersSpec requestHeadersSpec;

    PlayerSummary testPlayer1 = new PlayerSummary(1630173L, "Precious", "Achiuwa", LocalDateTime.parse("1999-09-19T00:00:00"), "6-8", (short) 243, (byte) 4, (byte) 5, "Forward", 1610612752L, "New York", "Knicks", "Eastern", "Atlantic", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png", "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg");
    PlayerSummary testPlayer2 = new PlayerSummary(203500L, "Steven", "Adams", LocalDateTime.parse("1993-07-20T00:00:00"), "6-11", (short) 265, (byte) 10, (byte) 12, "Center", 1610612745L, "Houston", "Rockets", "Western", "Southwest", "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png", "https://cdn.nba.com/logos/nba/1610612745/global/L/logo.svg");
    PlayerSummary testPlayer3 = new PlayerSummary(1628389L, "Bam", "Adebayo", LocalDateTime.parse("1997-07-18T00:00:00"), "6-9", (short) 255, (byte) 7, (byte) 13, "Center-Forward", 1610612748L, "Miami", "Heat", "Eastern", "Southeast", "https://cdn.nba.com/headshots/nba/latest/1040x760/1628389.png", "https://cdn.nba.com/logos/nba/1610612748/global/L/logo.svg");

    @BeforeEach
    void setUp() {
        playerService = new PlayerService(webClient);
    }


    @Test
    void testGetAllPlayers() {
        PlayerSummaryResponse testResponse = new PlayerSummaryResponse(
                List.of(testPlayer1, testPlayer2, testPlayer3),
                1,
                2,
                0,
                false,
                615
        );

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.exchangeToMono(any(Function.class)))
                .thenReturn(Mono.just(testResponse));

        StepVerifier.create(playerService.getPlayers(1, 3))
                .expectNextMatches(response -> {
                    boolean metadataValid = response.getCurrentPage() == 1 &&
                            response.getNextPage() == 2 &&
                            response.getPreviousPage() == 0 &&
                            !response.getIsLastPage() &&
                            response.getTotalPlayers() == 615;

                    boolean playersValid = response.getPlayers().size() == 3 &&
                            response.getPlayers().get(0).getPlayerId() == 1630173L &&
                            response.getPlayers().get(0).getFirstName().equals("Precious") &&
                            response.getPlayers().get(0).getLastName().equals("Achiuwa") &&
                            response.getPlayers().get(1).getPlayerId() == 203500L &&
                            response.getPlayers().get(1).getFirstName().equals("Steven") &&
                            response.getPlayers().get(1).getLastName().equals("Adams") &&
                            response.getPlayers().get(2).getPlayerId() == 1628389L &&
                            response.getPlayers().get(2).getFirstName().equals("Bam") &&
                            response.getPlayers().get(2).getLastName().equals("Adebayo");

                    return metadataValid && playersValid;
                })
                .verifyComplete();
    }

    @Test
    void testGetPlayers_WithEmptyResponse() {
        PlayerSummaryResponse emptyResponse = new PlayerSummaryResponse(
                List.of(),
                1,
                null,
                null,
                true,
                0
        );

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.exchangeToMono(any(Function.class)))
                .thenReturn(Mono.just(emptyResponse));

        StepVerifier.create(playerService.getPlayers(1, 3))
                .expectNextMatches(response ->
                        response.getPlayers().isEmpty() &&
                                response.getIsLastPage() &&
                                response.getTotalPlayers() == 0)
                .verifyComplete();
    }

    @Test
    void testGetByPlayerId_ReturnPlayerSummary() {
        PlayerSummary testPlayer1 = new PlayerSummary(1630173L, "Precious", "Achiuwa", LocalDateTime.parse("1999-09-19T00:00:00"), "6-8", (short) 243, (byte) 4, (byte) 5, "Forward", 1610612752L, "New York", "Knicks", "Eastern", "Atlantic", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png", "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg");

        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.exchangeToMono(any(Function.class)))
                .thenReturn(Mono.just(testPlayer1));

        StepVerifier.create(playerService.getPlayerById(testPlayer1.getPlayerId()))
                .expectNextMatches(response -> response.getFirstName().equals("Precious") &&
                        response.getLastName().equals("Achiuwa") &&
                        response.getTeamId() == 1610612752 &&
                        response.getTeamCity().equals("New York") &&
                        response.getTeamName().equals("Knicks"))
                .verifyComplete();
    }

    @Test
    void testGetByPlayerId_PlayerNotFound() {
        when(webClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri(any(Function.class))).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.accept(MediaType.APPLICATION_JSON)).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.exchangeToMono(any(Function.class)))
                .thenReturn(Mono.error(new PlayerNotFoundException("No player found")));

        StepVerifier.create(playerService.getPlayerById(145524323L))
                .expectErrorMatches(throwable ->
                        throwable instanceof PlayerNotFoundException &&
                                throwable.getMessage().equals("No player found"))
                .verify();
    }
}
