package com.khalilgayle.courtvisionserver.teams;

import com.khalilgayle.courtvisionserver.players.PlayerSummary;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class NbaTeamController {
    private final NbaTeamService nbaTeamService;

    public NbaTeamController(NbaTeamService nbaTeamService) {
        this.nbaTeamService = nbaTeamService;
    }

    @GetMapping("/teams")
    public Flux<NbaTeam> getTeams() {
        return nbaTeamService.getTeams();
    }

    @GetMapping("/teams/{teamId}/players")
    public Flux<PlayerSummary> getTeamRoster(@PathVariable long teamId) {
        return nbaTeamService.getTeamRoster(teamId);
    }
}
