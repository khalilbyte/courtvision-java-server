package com.khalilgayle.courtvisionserver.playertests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.khalilgayle.courtvisionserver.errorhandling.PlayerNotFoundException;
import com.khalilgayle.courtvisionserver.players.PlayerSummary;
import com.khalilgayle.courtvisionserver.players.PlayerSummaryResponse;
import com.khalilgayle.courtvisionserver.players.PlayerService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class PlayerMockWebServerTest {
    private static MockWebServer mockWebServer;
    private PlayerService playerService;
    private static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    @BeforeAll
    static void setup() throws IOException {
        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @BeforeEach
    void initialize() {
        String baseUrl = String.format("http://localhost:%s", mockWebServer.getPort());
        playerService = new PlayerService(WebClient.builder().baseUrl(baseUrl).build());
    }


    @Test
    void testGetAllPlayers() throws JsonProcessingException, InterruptedException {
        PlayerSummary player1 = new PlayerSummary(1630173L, "Precious", "Achiuwa", LocalDateTime.parse("1999-09-19T00:00:00"), "6-8", (short) 243, (byte) 4, (byte) 5, "Forward", 1610612752L, "New York", "Knicks", "Eastern", "Atlantic", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png", "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg");
        PlayerSummary player2 = new PlayerSummary(203500L, "Steven", "Adams", LocalDateTime.parse("1993-07-20T00:00:00"), "6-11", (short) 265, (byte) 10, (byte) 12, "Center", 1610612745L, "Houston", "Rockets", "Western", "Southwest", "https://cdn.nba.com/headshots/nba/latest/1040x760/203500.png", "https://cdn.nba.com/logos/nba/1610612745/global/L/logo.svg");
        PlayerSummary player3 = new PlayerSummary(1628389L, "Bam", "Adebayo", LocalDateTime.parse("1997-07-18T00:00:00"), "6-9", (short) 255, (byte) 7, (byte) 13, "Center-Forward", 1610612748L, "Miami", "Heat", "Eastern", "Southeast", "https://cdn.nba.com/headshots/nba/latest/1040x760/1628389.png", "https://cdn.nba.com/logos/nba/1610612748/global/L/logo.svg");

        List<PlayerSummary> listOfPlayers = List.of(player1, player2, player3);
        PlayerSummaryResponse playerSummaryResponse = new PlayerSummaryResponse(listOfPlayers, 1, 2, null, false, 615);

        String jsonResponse = objectMapper.writeValueAsString(playerSummaryResponse);

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(playerService.getPlayers(1, 3))
                .expectNextMatches(response -> {
                    boolean isMetadataValid = response.getCurrentPage() == 1 &&
                            response.getNextPage() == 2 &&
                            response.getPreviousPage() == null &&
                            !response.getIsLastPage() &&
                            response.getTotalPlayers() == 615;

                    boolean isPlayersValid = response.getPlayers().size() == 3 &&
                            response.getPlayers().get(0).getPlayerId() == 1630173L &&
                            response.getPlayers().get(0).getFirstName().equals("Precious") &&
                            response.getPlayers().get(0).getLastName().equals("Achiuwa") &&
                            response.getPlayers().get(1).getPlayerId() == 203500L &&
                            response.getPlayers().get(1).getFirstName().equals("Steven") &&
                            response.getPlayers().get(1).getLastName().equals("Adams") &&
                            response.getPlayers().get(2).getPlayerId() == 1628389L &&
                            response.getPlayers().get(2).getFirstName().equals("Bam") &&
                            response.getPlayers().get(2).getLastName().equals("Adebayo");

                    return isMetadataValid && isPlayersValid;
                })
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players?page=1&players_per_page=3", recordedRequest.getPath());
    }

    @Test
    void testGetPlayers_WithEmptyResponse() throws JsonProcessingException, InterruptedException {
        PlayerSummaryResponse emptyResponse = new PlayerSummaryResponse(
                List.of(),
                1,
                null,
                null,
                true,
                0
        );

        String jsonResponse = objectMapper.writeValueAsString(emptyResponse);

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));


        StepVerifier.create(playerService.getPlayers(1, 3))
                .expectNextMatches(response ->
                        response.getPlayers().isEmpty() &&
                                response.getIsLastPage() &&
                                response.getTotalPlayers() == 0)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players?page=1&players_per_page=3", recordedRequest.getPath());
    }

    @Test
    void testGetByPlayerId_ReturnPlayerSummary() throws JsonProcessingException, InterruptedException {
        PlayerSummary player1 = new PlayerSummary(1630173L, "Precious", "Achiuwa", LocalDateTime.parse("1999-09-19T00:00:00"), "6-8", (short) 243, (byte) 4, (byte) 5, "Forward", 1610612752L, "New York", "Knicks", "Eastern", "Atlantic", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630173.png", "https://cdn.nba.com/logos/nba/1610612752/global/L/logo.svg");

        String jsonResponse = objectMapper.writeValueAsString(player1);

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(playerService.getPlayerById(player1.getPlayerId()))
                .expectNextMatches(response ->
                        response.getFirstName().equals("Precious") &&
                                response.getLastName().equals("Achiuwa") &&
                                response.getTeamId() == 1610612752 &&
                                response.getTeamCity().equals("New York") &&
                                response.getTeamName().equals("Knicks"))
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players?player_id=1630173", recordedRequest.getPath());
    }

    @Test
    void testGetByPlayerId_PlayerNotFound() throws JsonProcessingException, InterruptedException {
        PlayerNotFoundException ex = new PlayerNotFoundException("No player found");
        String jsonResponse = objectMapper.writeValueAsString(ex);

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse)
                .setResponseCode(404));

        StepVerifier.create(playerService.getPlayerById(145524323))
                .expectErrorMatches(throwable ->
                        throwable instanceof PlayerNotFoundException &&
                                throwable.getMessage().equals("No player found"))
                .verify();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players?player_id=145524323", recordedRequest.getPath());
    }

    @Test
    void testGetPlayersBySearch() throws JsonProcessingException, InterruptedException {
        PlayerSummary player1 = new PlayerSummary(1630163L, "LaMelo", "Ball", LocalDateTime.parse("2001-08-22T00:00:00"), "6-7", (short) 180, (byte) 4, (byte) 1, "Guard", 1610612766L, "Charlotte", "Hornets", "Eastern", "Southeast", "https://cdn.nba.com/headshots/nba/latest/1040x760/1630163.png", "https://cdn.nba.com/logos/nba/1610612766/global/L/logo.svg");
        PlayerSummary player2 = new PlayerSummary(1628366L, "Lonzo", "Ball", LocalDateTime.parse("1997-10-27T00:00:00"), "6-6", (short) 190, (byte) 5, (byte) 2, "Guard", 1610612741L, "Chicago", "Bulls", "Eastern", "Central", "https://cdn.nba.com/headshots/nba/latest/1040x760/1628366.png", "https://cdn.nba.com/logos/nba/1610612741/global/L/logo.svg");
        String jsonResponse = objectMapper.writeValueAsString(List.of(player1, player2));

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(playerService.getPlayersBySearch("Ball"))
                .expectNext(player1)
                .expectNext(player2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players/search?keyword=Ball", recordedRequest.getPath());
    }

    @Test
    void testGetPlayersBySearch_EmptyKeyword() throws JsonProcessingException, InterruptedException {
        String jsonResponse = "[]";

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(playerService.getPlayersBySearch("4243f2f2fwsf"))
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players/search?keyword=4243f2f2fwsf", recordedRequest.getPath());
    }
}
