package com.khalilgayle.courtvisionserver.players;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerSummaryResponse(
        @JsonProperty("players") List<PlayerSummary> players,
        @JsonProperty("current_page") Integer currentPage,
        @JsonProperty("next_page") Integer nextPage,
        @JsonProperty("previous_page") Integer previousPage,
        @JsonProperty("is_last_page") Boolean isLastPage,
        @JsonProperty("total_players") Integer totalPlayers
) {
}