package com.khalilgayle.courtvisionserver.teamtests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.khalilgayle.courtvisionserver.players.PlayerSummary;
import com.khalilgayle.courtvisionserver.teams.NbaTeam;
import com.khalilgayle.courtvisionserver.teams.NbaTeamService;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.jupiter.api.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.test.StepVerifier;

import java.io.IOException;
import java.time.LocalDateTime;

public class NbaTeamMockWebServerTest {
    private static MockWebServer mockWebServer;
    private NbaTeamService nbaTeamService;
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
        nbaTeamService = new NbaTeamService(WebClient.builder().baseUrl(baseUrl).build());
    }

    @Test
    void getAllTeams() throws JsonProcessingException, InterruptedException {
        NbaTeam team1 = new NbaTeam(1610612747L, "Los Angeles Lakers", "LAL", "Lakers", "Los Angeles", "https://cdn.nba.com/logos/nba/1610612747/global/L/logo.svg", "Western", "Pacific");
        NbaTeam team2 = new NbaTeam(1610612744L, "Golden State Warriors", "GSW", "Warriors", "Golden State", "https://cdn.nba.com/logos/nba/1610612744/global/L/logo.svg", "Western", "Pacific");
        NbaTeam team3 = new NbaTeam(1610612738L, "Boston Celtics", "BOS", "Celtics", "Boston", "https://cdn.nba.com/logos/nba/1610612738/global/L/logo.svg", "Eastern", "Atlantic");
        NbaTeam team4 = new NbaTeam(1610612748L, "Miami Heat", "MIA", "Heat", "Miami", "https://cdn.nba.com/logos/nba/1610612748/global/L/logo.svg", "Eastern", "Southeast");
        NbaTeam team5 = new NbaTeam(1610612749L, "Milwaukee Bucks", "MIL", "Bucks", "Milwaukee", "https://cdn.nba.com/logos/nba/1610612749/global/L/logo.svg", "Eastern", "Central");

        String jsonResponse = objectMapper.writeValueAsString(new NbaTeam[]{team1, team2, team3, team4, team5});

        mockWebServer.enqueue(new MockResponse()
                .addHeader("Content-Type", "application/json")
                .setBody(jsonResponse));

        StepVerifier.create(nbaTeamService.getTeams())
                .expectNext(team1)
                .expectNext(team2)
                .expectNext(team3)
                .expectNext(team4)
                .expectNext(team5)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/teams", recordedRequest.getPath());
    }

    @Test
    void getTeamRosterById() throws JsonProcessingException, InterruptedException {
        long lakersTeamId = 1610612747;
        PlayerSummary player1 = new PlayerSummary(1641720L, "Jalen", "Hood-Schifino", LocalDateTime.parse("2003-06-19T00:00:00"), "6-5", (short) 210, (byte) 1, (byte) 0, "G", 1610612747L, "Los Angeles", "Lakers", "Western", "Pacific", "https://cdn.nba.com/headshots/nba/latest/1040x760/1641720.png", "https://cdn.nba.com/logos/nba/1610612747/global/L/logo.svg");
        PlayerSummary player2 = new PlayerSummary(1626156L, "D'Angelo", "Russell", LocalDateTime.parse("1996-02-23T00:00:00"), "6-3", (short) 193, (byte) 9, (byte) 0, "G", 1610612747L, "Los Angeles", "Lakers", "Western", "Pacific", "https://cdn.nba.com/headshots/nba/latest/1040x760/1626156.png", "https://cdn.nba.com/logos/nba/1610612747/global/L/logo.svg");
        PlayerSummary player3 = new PlayerSummary(1629020L, "Jarred", "Vanderbilt", LocalDateTime.parse("1999-04-03T00:00:00"), "6-8", (short) 214, (byte) 6, (byte) 0, "F", 1610612747L, "Los Angeles", "Lakers", "Western", "Pacific", "https://cdn.nba.com/headshots/nba/latest/1040x760/1629020.png", "https://cdn.nba.com/logos/nba/1610612747/global/L/logo.svg");

        String jsonResponse = objectMapper.writeValueAsString(new PlayerSummary[]{player1, player2, player3});

        mockWebServer.enqueue(new MockResponse()
                .setBody(jsonResponse)
                .addHeader("Content-Type", "application/json"));

        StepVerifier.create(nbaTeamService.getTeamRoster(lakersTeamId))
                .expectNext(player1)
                .expectNext(player2)
                .expectNext(player3)
                .verifyComplete();

        RecordedRequest recordedRequest = mockWebServer.takeRequest();
        Assertions.assertEquals("GET", recordedRequest.getMethod());
        Assertions.assertEquals("/teams/1610612747/players", recordedRequest.getPath());
    }
}
