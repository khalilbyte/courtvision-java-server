package com.khalilgayle.courtvisionserver.players;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/search")
    public Flux<PlayerSummary> getPlayersBySearch(@RequestParam(value = "keyword") String keyword) {
        return playerService.getPlayersBySearch(keyword);
    }

    @GetMapping("/players/{playerId}")
    public Mono<PlayerSummary> getByPlayerId(@PathVariable long playerId) {
        return playerService.getPlayerById(playerId);
    }

    @GetMapping("/players")
    public Mono<PlayerSummaryResponse> getPlayers(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "players_per_page", defaultValue = "10") int playersPerPage
    ) {
        return playerService.getPlayers(page, playersPerPage);
    }
}