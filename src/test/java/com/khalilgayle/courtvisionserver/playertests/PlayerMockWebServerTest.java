package com.khalilgayle.courtvisionserver.playertests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.khalilgayle.courtvisionserver.errorhandling.PlayerNotFoundException;
import com.khalilgayle.courtvisionserver.players.*;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
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
                    boolean isMetadataValid = response.currentPage() == 1 &&
                            response.nextPage() == 2 &&
                            response.previousPage() == null &&
                            !response.isLastPage() &&
                            response.totalPlayers() == 615;

                    boolean isPlayersValid = response.players().size() == 3 &&
                            response.players().get(0).playerId() == 1630173L &&
                            response.players().get(0).firstName().equals("Precious") &&
                            response.players().get(0).lastName().equals("Achiuwa") &&
                            response.players().get(1).playerId() == 203500L &&
                            response.players().get(1).firstName().equals("Steven") &&
                            response.players().get(1).lastName().equals("Adams") &&
                            response.players().get(2).playerId() == 1628389L &&
                            response.players().get(2).firstName().equals("Bam") &&
                            response.players().get(2).lastName().equals("Adebayo");

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
                        response.players().isEmpty() &&
                                response.isLastPage() &&
                                response.totalPlayers() == 0)
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

        StepVerifier.create(playerService.getPlayerById(player1.playerId()))
                .expectNextMatches(response ->
                        response.firstName().equals("Precious") &&
                                response.lastName().equals("Achiuwa") &&
                                response.teamId() == 1610612752 &&
                                response.teamCity().equals("New York") &&
                                response.teamName().equals("Knicks"))
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

    @Test
    void testGetPlayersByCategoryLeaders() throws JsonProcessingException, InterruptedException {
        PlayerCategoryLeader player1 = new PlayerCategoryLeader(
                203507L,                 // playerId
                "Giannis Antetokounmpo", // playerName
                1,                       // rank
                1610612749L,            // teamId
                24,                     // gamesPlayed
                12.8f,                  // minutesPlayed
                20.9f,                  // fgm
                20.9f,                  // fga
                0.613f,                 // fgpct
                0.2f,                   // fg3m
                0.8f,                   // fg3a
                0.222f,                 // fg3_pct
                7.0f,                   // ftm
                11.3f,                  // fta
                0.614f,                 // ftpct
                2.0f,                   // oreb
                9.6f,                   // dreb
                11.6f,                  // reb
                6.0f,                   // ast
                0.7f,                   // stl
                1.5f,                   // blk
                3.4f,                   // tov
                32.7f,                  // pts
                36.6f                   // eff
        );

        PlayerCategoryLeader player2 = new PlayerCategoryLeader(
                1628983L,                      // playerId
                "Shai Gilgeous-Alexander",     // playerName
                2,                             // rank
                1610612760L,                   // teamId
                31,                           // gamesPlayed
                10.9f,                        // minutesPlayed
                20.9f,                        // fgm
                20.9f,                        // fga
                0.523f,                       // fgpct
                2.1f,                         // fg3m
                6.2f,                         // fg3a
                0.344f,                       // fg3_pct
                7.0f,                         // ftm
                7.9f,                         // fta
                0.882f,                       // ftpct
                0.8f,                         // oreb
                4.8f,                         // dreb
                5.6f,                         // reb
                6.2f,                         // ast
                1.9f,                         // stl
                1.2f,                         // blk
                2.7f,                         // tov
                31.0f,                        // pts
                32.3f                         // eff
        );

        String jsonResponse = objectMapper.writeValueAsString(List.of(player1, player2));

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(playerService.getCategoryLeaders(2, "PTS"))
                .expectNext(player1)
                .expectNext(player2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players/categories?number_of_players=2" +
                        "&category=PTS",
                recordedRequest.getPath());
    }

    @Test
    void getCareerAverages() throws JsonProcessingException, InterruptedException {
        // Arrange
        long paoloBancheroId = 1631094L; // Paolo Banchero's NBA ID

        List<PlayerAverages> seasonAverages = List.of(
                new PlayerAverages(paoloBancheroId, "2022-23", 1610612753L, "ORL", 20, 72, 72, 33.8f, 6.6f, 15.3f, 0.427f, 1.2f, 3.9f, 0.299f, 5.4f, 7.0f, 0.765f, 0.9f, 5.4f, 6.9f, 3.7f, 1.0f, 0.5f, 2.8f, 2.6f, 20.0f),
                new PlayerAverages(paoloBancheroId, "2023-24", 1610612753L, "ORL", 21, 57, 57, 34.1f, 7.6f, 16.5f, 0.461f, 1.5f, 4.6f, 0.324f, 5.5f, 7.0f, 0.787f, 1.0f, 6.0f, 7.0f, 4.9f, 1.0f, 0.6f, 2.7f, 2.4f, 22.2f)
        );

        String jsonResponse = objectMapper.writeValueAsString(seasonAverages);

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        // Act
        StepVerifier.create(playerService.getCareerAverages(paoloBancheroId))
                // Assert
                .expectNextCount(2)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players/1631094/career-averages", recordedRequest.getPath());
    }

    @Test
    void getCareerAverages_InvalidId() throws InterruptedException {
        // Arrange
        long invalidPlayerId = 13211432422L;

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody("[]"));

        // Act
        Flux<PlayerAverages> result = playerService.getCareerAverages(invalidPlayerId);

        // Assert
        StepVerifier.create(result)
                .expectNextCount(0)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/players/13211432422/career-averages", recordedRequest.getPath());
    }
}
