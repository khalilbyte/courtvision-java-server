package com.khalilgayle.courtvisionserver.players;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlayerCategoryLeader(
        @JsonProperty("player_id") Long playerId,
        @JsonProperty("player_name") String playerName,
        @JsonProperty("rank") Integer rank,
        @JsonProperty("team_id") Long teamId,
        @JsonProperty("games_played") Integer gamesPlayed,
        @JsonProperty("minutes_played") Float minutesPlayed,
        @JsonProperty("fgm") Float fgm,
        @JsonProperty("fga") Float fga,
        @JsonProperty("fg_pct") Float fgPct,
        @JsonProperty("fg3m") Float fg3m,
        @JsonProperty("fg3a") Float fg3a,
        @JsonProperty("fg3_pct") Float fg3Pct,
        @JsonProperty("ftm") Float ftm,
        @JsonProperty("fta") Float fta,
        @JsonProperty("ft_pct") Float ftPct,
        @JsonProperty("oreb") Float oreb,
        @JsonProperty("dreb") Float dreb,
        @JsonProperty("reb") Float reb,
        @JsonProperty("ast") Float ast,
        @JsonProperty("stl") Float stl,
        @JsonProperty("blk") Float blk,
        @JsonProperty("tov") Float tov,
        @JsonProperty("pts") Float pts,
        @JsonProperty("eff") Float eff
) {}