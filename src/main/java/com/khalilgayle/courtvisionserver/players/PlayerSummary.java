package com.khalilgayle.courtvisionserver.players;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public record PlayerSummary(
        @JsonProperty("player_id") Long playerId,
        @JsonProperty("first_name") String firstName,
        @JsonProperty("last_name") String lastName,
        @JsonProperty("birth_date") @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime birthDate,
        String height,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Short weight,
        @JsonProperty("season_exp") Byte seasonExp,
        @JsonFormat(shape = JsonFormat.Shape.STRING) Byte jersey,
        String position,
        @JsonProperty("team_id") Long teamId,
        @JsonProperty("team_city") String teamCity,
        @JsonProperty("team_name") String teamName,
        String conference,
        String division,
        @JsonProperty("player_image_url") String playerImageUrl,
        @JsonProperty("team_image_url") String teamImageUrl
) {
    public int getAge() {
        return Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        return "PlayerSummary{" +
                "playerId=" + playerId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate='" + birthDate.format(formatter) + " (Age: " + getAge() + ")'" +
                ", height='" + height + '\'' +
                ", weight='" + weight + '\'' +
                ", seasonExp=" + seasonExp +
                ", jersey='" + jersey + '\'' +
                ", position='" + position + '\'' +
                ", teamId=" + teamId +
                ", teamCity='" + teamCity + '\'' +
                ", teamName='" + teamName + '\'' +
                ", conference='" + conference + '\'' +
                ", division='" + division + '\'' +
                ", playerImageUrl='" + playerImageUrl + '\'' +
                ", teamImageUrl='" + teamImageUrl + '\'' +
                '}';
    }
}