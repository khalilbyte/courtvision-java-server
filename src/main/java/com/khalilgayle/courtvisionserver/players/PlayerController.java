package com.khalilgayle.courtvisionserver.players;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
    import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
public class PlayerController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players/categories")
    public Flux<PlayerCategoryLeader> getPlayersByCategoryLeader(
            @RequestParam(value = "number_of_players") int numberOfPlayers,
            @RequestParam(value = "category") String category
    ) {
        return playerService.getCategoryLeaders(numberOfPlayers, category);
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
        LOGGER.info("Request made: page = {}, playerPerPage = {}", page, playersPerPage);
        return playerService.getPlayers(page, playersPerPage);
    }
}