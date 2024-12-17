package com.khalilgayle.courtvisionserver.playertests;

import com.khalilgayle.courtvisionserver.players.PlayerListDTO;
import com.khalilgayle.courtvisionserver.players.PlayerListDTOResponse;
import com.khalilgayle.courtvisionserver.players.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
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

    PlayerListDTO testPlayer1 = new PlayerListDTO(1630173L, "Precious", "Achiuwa", LocalDateTime.parse("1999-09-19T00:00:00"), "6-8", (short) 243, (byte) 4, (byte) 5, "Forward", 1610612752L, "New York", "Knicks", "Eastern", "Atlantic", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png", "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg");
    PlayerListDTO testPlayer2 = new PlayerListDTO(203500L, "Steven", "Adams", LocalDateTime.parse("1993-07-20T00:00:00"), "6-11", (short) 265, (byte) 10, (byte) 12, "Center", 1610612745L, "Houston", "Rockets", "Western", "Southwest", "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png", "https://cdn.nba.com/logos/nba/1610612745/global/L/logo.svg");
    PlayerListDTO testPlayer3 = new PlayerListDTO(1628389L, "Bam", "Adebayo", LocalDateTime.parse("1997-07-18T00:00:00"), "6-9", (short) 255, (byte) 7, (byte) 13, "Center-Forward", 1610612748L, "Miami", "Heat", "Eastern", "Southeast", "https://cdn.nba.com/headshots/nba/latest/1040x760/1628389.png", "https://cdn.nba.com/logos/nba/1610612748/global/L/logo.svg");

    @BeforeEach
    void setUp() {
        playerService = new PlayerService(webClient);
    }


    @Test
    void testGetAllPlayers() {
        PlayerListDTOResponse testResponse = new PlayerListDTOResponse(
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
}
