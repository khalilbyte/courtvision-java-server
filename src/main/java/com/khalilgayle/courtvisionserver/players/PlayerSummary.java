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
    @JsonProperty("birth_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    LocalDateTime birthDate;
    private String height;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Short weight;
    @JsonProperty("season_exp")
    private Byte seasonExp;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Byte jersey;
    private String position;
    @JsonProperty("team_id")
    private Long teamId;
    @JsonProperty("team_city")
    private String teamCity;
    @JsonProperty("team_name")
    private String teamName;
    private String conference;
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

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Short getWeight() {
        return weight;
    }

    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public Byte getSeasonExp() {
        return seasonExp;
    }

    public void setSeasonExp(Byte seasonExp) {
        this.seasonExp = seasonExp;
    }

    public Byte getJersey() {
        return jersey;
    }

    public void setJersey(Byte jersey) {
        this.jersey = jersey;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public String getPlayerImageUrl() {
        return playerImageUrl;
    }

    public void setPlayerImageUrl(String playerImageUrl) {
        this.playerImageUrl = playerImageUrl;
    }

    public String getTeamImageUrl() {
        return teamImageUrl;
    }

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSummary that = (PlayerSummary) o;
        return Objects.equals(getPlayerId(), that.getPlayerId()) && Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName()) && Objects.equals(getBirthDate(), that.getBirthDate()) && Objects.equals(getHeight(), that.getHeight()) && Objects.equals(getWeight(), that.getWeight()) && Objects.equals(getSeasonExp(), that.getSeasonExp()) && Objects.equals(getJersey(), that.getJersey()) && Objects.equals(getPosition(), that.getPosition()) && Objects.equals(getTeamId(), that.getTeamId()) && Objects.equals(getTeamCity(), that.getTeamCity()) && Objects.equals(getTeamName(), that.getTeamName()) && Objects.equals(getConference(), that.getConference()) && Objects.equals(getDivision(), that.getDivision()) && Objects.equals(getPlayerImageUrl(), that.getPlayerImageUrl()) && Objects.equals(getTeamImageUrl(), that.getTeamImageUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getFirstName(), getLastName(), getBirthDate(), getHeight(), getWeight(), getSeasonExp(), getJersey(), getPosition(), getTeamId(), getTeamCity(), getTeamName(), getConference(), getDivision(), getPlayerImageUrl(), getTeamImageUrl());
    }
}