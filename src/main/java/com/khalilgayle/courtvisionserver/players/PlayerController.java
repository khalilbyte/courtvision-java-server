package com.khalilgayle.courtvisionserver.players;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public Mono<PlayerSummaryResponse> getPlayers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "players_per_page", defaultValue = "10") int playersPerPage
    ) {
        return playerService.getPlayers(page, playersPerPage);
    }

    @GetMapping("/players/{playerId}")
    public Mono<PlayerSummary> getByPlayerId(long playerId) {
        return playerService.getPlayerById(playerId);
    }
}