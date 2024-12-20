package com.khalilgayle.courtvisionserver.players;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@JsonPropertyOrder({
        "player_id",
        "first_name",
        "last_name",
        "birth_date",
        "height",
        "weight",
        "season_exp",
        "jersey",
        "position",
        "team_id",
        "team_city",
        "team_name",
        "conference",
        "division",
        "player_image_url",
        "team_image_url"
})

public class PlayerSummary {
    @JsonProperty("player_id")
    private Long playerId;
    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime birthDate;
    @JsonProperty("height")
    private String height;
    @JsonProperty("weight")
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Short weight;
    @JsonProperty("season_exp")
    private Byte seasonExp;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @JsonProperty("jersey")
    private Byte jersey;
    @JsonProperty("position")
    private String position;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("team_city")
    private String teamCity;
    @JsonProperty("team_name")
    private String teamName;
    @JsonProperty("conference")
    private String conference;
    @JsonProperty("division")
    private String division;
    @JsonProperty("player_image_url")
    private String playerImageUrl;
    @JsonProperty("team_image_url")
    private String teamImageUrl;

    public PlayerSummary() {
    }

    public PlayerSummary(Long playerId, String firstName, String lastName, LocalDateTime birthDate, String height, Short weight, Byte seasonExp, Byte jersey, String position, Long teamId, String teamCity, String teamName, String conference, String division, String playerImageUrl, String teamImageUrl) {
        super();
        this.playerId = playerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.height = height;
        this.weight = weight;
        this.seasonExp = seasonExp;
        this.jersey = jersey;
        this.position = position;
        this.teamId = teamId;
        this.teamCity = teamCity;
        this.teamName = teamName;
        this.conference = conference;
        this.division = division;
        this.playerImageUrl = playerImageUrl;
        this.teamImageUrl = teamImageUrl;
    }

    @JsonProperty("player_id")
    public Long getPlayerId() {
        return playerId;
    }

    @JsonProperty("player_id")
    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    @JsonProperty("first_name")
    public String getFirstName() {
        return firstName;
    }

    @JsonProperty("first_name")
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @JsonProperty("last_name")
    public String getLastName() {
        return lastName;
    }

    @JsonProperty("last_name")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @JsonProperty("birth_date")
    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    @JsonProperty("birth_date")
    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    @JsonProperty("height")
    public String getHeight() {
        return height;
    }

    @JsonProperty("height")
    public void setHeight(String height) {
        this.height = height;
    }

    @JsonProperty("weight")
    public Short getWeight() {
        return weight;
    }

    @JsonProperty("weight")
    public void setWeight(Short weight) {
        this.weight = weight;
    }

    @JsonProperty("season_exp")
    public Byte getSeasonExp() {
        return seasonExp;
    }

    @JsonProperty("season_exp")
    public void setSeasonExp(Byte seasonExp) {
        this.seasonExp = seasonExp;
    }

    @JsonProperty("jersey")
    public Byte getJersey() {
        return jersey;
    }

    @JsonProperty("jersey")
    public void setJersey(Byte jersey) {
        this.jersey = jersey;
    }

    @JsonProperty("position")
    public String getPosition() {
        return position;
    }

    @JsonProperty("position")
    public void setPosition(String position) {
        this.position = position;
    }

    @JsonProperty("team_id")
    public Long getTeamId() {
        return teamId;
    }

    @JsonProperty("team_id")
    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    @JsonProperty("team_city")
    public String getTeamCity() {
        return teamCity;
    }

    @JsonProperty("team_city")
    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    @JsonProperty("team_name")
    public String getTeamName() {
        return teamName;
    }

    @JsonProperty("team_name")
    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @JsonProperty("conference")
    public String getConference() {
        return conference;
    }

    @JsonProperty("conference")
    public void setConference(String conference) {
        this.conference = conference;
    }

    @JsonProperty("division")
    public String getDivision() {
        return division;
    }

    @JsonProperty("division")
    public void setDivision(String division) {
        this.division = division;
    }

    @JsonProperty("player_image_url")
    public String getPlayerImageUrl() {
        return playerImageUrl;
    }

    @JsonProperty("player_image_url")
    public void setPlayerImageUrl(String playerImageUrl) {
        this.playerImageUrl = playerImageUrl;
    }

    @JsonProperty("team_image_url")
    public String getTeamImageUrl() {
        return teamImageUrl;
    }

    @JsonProperty("team_image_url")
    public void setTeamImageUrl(String teamImageUrl) {
        this.teamImageUrl = teamImageUrl;
    }

    public int getAge() {
        return Period.between(birthDate.toLocalDate(), LocalDate.now()).getYears();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlayerSummary{");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd, yyyy");
        sb.append("playerId=").append(playerId);
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthDate='").append(birthDate.format(formatter)).append(" (Age: ").append(getAge()).append(")").append('\'');
        sb.append(", height='").append(height).append('\'');
        sb.append(", weight='").append(weight).append('\'');
        sb.append(", seasonExp=").append(seasonExp);
        sb.append(", jersey='").append(jersey).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", teamId=").append(teamId);
        sb.append(", teamCity='").append(teamCity).append('\'');
        sb.append(", teamName='").append(teamName).append('\'');
        sb.append(", conference='").append(conference).append('\'');
        sb.append(", division='").append(division).append('\'');
        sb.append(", playerImageUrl='").append(playerImageUrl).append('\'');
        sb.append(", teamImageUrl='").append(teamImageUrl).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getFirstName(), getLastName(), getBirthDate(), getHeight(), getWeight(), getSeasonExp(), getJersey(), getPosition(), getTeamId(), getTeamCity(), getTeamName(), getConference(), getDivision(), getPlayerImageUrl(), getTeamImageUrl());
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PlayerSummary playerSummary)) {
            return false;
        }
        return Objects.equals(this.teamName, playerSummary.teamName)
                && Objects.equals(this.lastName, playerSummary.lastName)
                && Objects.equals(this.conference, playerSummary.conference)
                && Objects.equals(this.seasonExp, playerSummary.seasonExp)
                && Objects.equals(this.weight, playerSummary.weight)
                && Objects.equals(this.teamCity, playerSummary.teamCity)
                && Objects.equals(this.birthDate, playerSummary.birthDate)
                && Objects.equals(this.division, playerSummary.division)
                && Objects.equals(this.firstName, playerSummary.firstName)
                && Objects.equals(this.playerImageUrl, playerSummary.playerImageUrl)
                && Objects.equals(this.jersey, playerSummary.jersey)
                && Objects.equals(this.teamId, playerSummary.teamId)
                && Objects.equals(this.position, playerSummary.position)
                && Objects.equals(this.playerId, playerSummary.playerId)
                && Objects.equals(this.height, playerSummary.height)
                && Objects.equals(this.teamImageUrl, playerSummary.teamImageUrl);
    }

}